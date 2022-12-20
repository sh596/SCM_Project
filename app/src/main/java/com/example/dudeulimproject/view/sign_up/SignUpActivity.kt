package com.example.dudeulimproject.view.sign_up

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.dudeulimproject.NavigationFragment
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseActivity
import com.example.dudeulimproject.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val fragment : NavigationFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.navHostActivitySignUp) as NavigationFragment
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun clickNextButton(view: View) {
        fragment.clickNextButton()
    }
    fun clickBackButton(view: View){
        fragment.clickBackButton()
    }
}