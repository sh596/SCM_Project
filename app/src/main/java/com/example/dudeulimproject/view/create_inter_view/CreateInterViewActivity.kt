package com.example.dudeulimproject.view.create_inter_view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.Navigation
import com.example.dudeulimproject.NavigationFragment
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseActivity
import com.example.dudeulimproject.databinding.ActivityCreateInterViewBinding
import com.example.dudeulimproject.utils.Resource
import com.example.dudeulimproject.utils.State
import com.example.dudeulimproject.view.create_inter_view.viewmodel.CreateInterViewViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateInterViewActivity : BaseActivity<ActivityCreateInterViewBinding>(R.layout.activity_create_inter_view) {

    private val viewModel : CreateInterViewViewModel by viewModels()
    private val fragment by lazy {
        supportFragmentManager.fragments
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    fun clickNextButton(view: View) {
        when(viewModel.position.value){
            0 -> {
                if (viewModel.uri.value == null) {
                    toastMsg("썸네일을 선택해주세요")
                    return
                }
                if (viewModel.title.value == null) {
                    toastMsg("제목을 입력해주세요")
                    return
                }
                if (viewModel.description.value == null) {
                    toastMsg("설명을 입력해주세요")
                    return
                }
                Navigation.findNavController(binding.navHostActivityCreateInterView)
                    .navigate(R.id.actionCreateInterViewToFragement2)
                viewModel.setFragmentPosition(1)
            }
            1 -> {
                if(viewModel.amount.value == null){
                    toastMsg("금액을 입력해주세요")
                    return
                }
                viewModel.createInterView()
                finish()
            }
        }
    }
    fun clickBackButton(view: View){
        when(viewModel.position.value){
            0->{
                finish()
            }
            1 -> {
                Navigation.findNavController(binding.navHostActivityCreateInterView)
                    .navigate(R.id.actionCreateInterViewPopFragment2)
                viewModel.setFragmentPosition(0)
            }
        }

    }

    private fun toastMsg(msg: String) {
        Toast.makeText(binding.root.context, msg, Toast.LENGTH_SHORT).show()
    }
}
