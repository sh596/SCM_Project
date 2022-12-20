package com.example.dudeulimproject.view.sign_up

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.Navigation
import com.example.dudeulimproject.NavigationFragment
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseActivity
import com.example.dudeulimproject.databinding.ActivitySignUpBinding
import com.example.dudeulimproject.view.sign_up.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private var position = 0
    private val viewModel : SignUpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this
        binding.lifecycleOwner = this
    }
    fun clickNextButton(view: View) {
        when(position){
            0 -> {
                if( !(viewModel.nickname.value.isNullOrBlank()) ||viewModel.nickname.value!!.length in 4..12 ) {
                    Navigation.findNavController(binding.navHostActivitySignUp)
                        .navigate(R.id.action_signUpFragment1_to_signUpFragment2)
                }else {
                    Toast.makeText(binding.root.context, "입력 조건을 맞춰주세요", Toast.LENGTH_SHORT).show()
                }
            }
            1 ->{
                Navigation.findNavController(binding.navHostActivitySignUp)
                    .navigate(R.id.action_signUpFragment2_to_signUpFragment3)
            }
            2 -> {
                viewModel.signUp()
                finish()
            }
        }

        position++;
    }
    fun clickBackButton(view: View){
        when(position){
            0 -> {
                finish()
            }
            1 ->{
                Navigation.findNavController(binding.navHostActivitySignUp)
                    .navigate(R.id.actionPopSignUpFragment2)
            }
            2 -> {
                Navigation.findNavController(binding.navHostActivitySignUp)
                    .navigate(R.id.actionPopSignUpFragment3)
            }
        }
        position--;
    }
}