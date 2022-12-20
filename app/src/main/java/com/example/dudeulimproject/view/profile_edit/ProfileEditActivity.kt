package com.example.dudeulimproject.view.profile_edit

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseActivity
import com.example.dudeulimproject.databinding.ActivityProfileEditBinding
import com.example.dudeulimproject.utils.Resource
import com.example.dudeulimproject.utils.State
import com.example.dudeulimproject.view.profile_edit.viewmodel.ProfileEditViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileEditActivity : BaseActivity<ActivityProfileEditBinding>(R.layout.activity_profile_edit) {
    private val viewModel : ProfileEditViewModel by viewModels()
    private val resultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri: Uri? = result.data?.data
                if (uri != null) {
                    viewModel.setUri(uri)
                }
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.activity = this
        binding.lifecycleOwner = this
        viewModel.updaterResult.observe(this) {
            if (viewModel.updaterResult.value!!.status == State.Success) {
                finish()
            }else if(viewModel.updaterResult.value!!.status == State.Failed){
                Toast.makeText(this, "프로필 수정에 실패했습니다", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.getProfile()
    }

    fun clickImageButton(view: View) {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        resultLauncher.launch(intent)

    }

    fun clickSaveButton(view: View){
        if(viewModel.nickname.value!!.isBlank()){
            Toast.makeText(this, "닉네임을 입력해주세요", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.editProfile()
    }
}