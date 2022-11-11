package com.example.dudeulimproject.view.sign_up

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseActivity
import com.example.dudeulimproject.databinding.ActivitySignUp1Binding
import com.example.dudeulimproject.databinding.ActivitySignUp2Binding
import com.example.dudeulimproject.view.sign_up.viewmodel.SignUpNicknameViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity1 : BaseActivity<ActivitySignUp1Binding>(R.layout.activity_sign_up1) {
    private val viewModel by viewModels<SignUpNicknameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.activity = this
        binding.lifecycleOwner = this

    }

    fun clickBackButton(view : View) {
        finish()
    }

    fun clickNextButton(view : View) {
        if( !(viewModel.nickname.value.isNullOrBlank()) ||viewModel.nickname.value!!.length in 4..12 ) {
            val intent = Intent(this, SignUpActivity2::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("nickname", viewModel.nickname.value)
            startActivity(intent)
        }else {
            Toast.makeText(this, "입력 조건을 맞춰주세요", Toast.LENGTH_SHORT).show()
        }
    }

}