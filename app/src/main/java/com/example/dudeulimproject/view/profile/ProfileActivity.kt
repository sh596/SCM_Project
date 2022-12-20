package com.example.dudeulimproject.view.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseActivity
import com.example.dudeulimproject.databinding.ActivityProfileBinding
import com.example.dudeulimproject.databinding.DialogAlterBinding
import com.example.dudeulimproject.utils.State
import com.example.dudeulimproject.view.dialog.AlterDialog
import com.example.dudeulimproject.view.dialog.NormalDialog
import com.example.dudeulimproject.view.profile.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : BaseActivity<ActivityProfileBinding>(R.layout.activity_profile) {
    private val viewModel : ProfileViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        observeUserData()
        val id = intent.getStringExtra("userId")
        if(id != null) {
            viewModel.getProfile(id)
        }else {
            showAlterDialog()
        }
    }
    fun clickBackButton(view : View) {
        finish()
    }

    private fun observeUserData() {
        viewModel.user.observe(this){
            if(it.status == State.Failed) {
                showAlterDialog()
            }
        }
    }
    private fun showAlterDialog(){
        val dialog = AlterDialog(this, "프로필 오류", "프로필을 찾을 수 없습니다") { finish() }
        dialog.show()
    }
}