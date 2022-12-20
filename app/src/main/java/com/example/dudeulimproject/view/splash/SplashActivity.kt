package com.example.dudeulimproject.view.splash

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseActivity
import com.example.dudeulimproject.databinding.ActivitySplashBinding
import com.example.dudeulimproject.utils.State
import com.example.dudeulimproject.view.login.LoginActivity
import com.example.dudeulimproject.view.main.MainActivity
import com.example.dudeulimproject.view.splash.viewmodel.SplashViewModel
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    private lateinit var mAuth : FirebaseAuth
    private val viewModel : SplashViewModel by viewModels()
    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = mAuth.currentUser
        if (user != null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            signInAnonymously()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        viewModel.checkResult.observe(this, Observer {
            if(it.status == State.Success){
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else if(it.status == State.Failed) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        })
        viewModel.checkLogin()
    }
    private fun signInAnonymously() {
        mAuth.signInAnonymously().addOnSuccessListener(this, OnSuccessListener<AuthResult?> {
            // do your stuff
        })
            .addOnFailureListener(this,
                OnFailureListener { exception ->
                    Log.e(
                        TAG,
                        "signInAnonymously:FAILURE",
                        exception
                    )
                })
    }
}