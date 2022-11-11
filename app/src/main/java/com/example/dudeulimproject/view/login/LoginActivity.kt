package com.example.dudeulimproject.view.login

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseActivity
import com.example.dudeulimproject.databinding.ActivityLoginBinding
import com.example.dudeulimproject.utils.State
import com.example.dudeulimproject.view.login.viewmodel.LoginViewModel
import com.example.dudeulimproject.view.main.MainActivity
import com.example.dudeulimproject.view.sign_up.SignUpActivity1
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel by viewModels<LoginViewModel>()

    private var auth: FirebaseAuth? = null
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var gso: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this
        initGsc()
        viewModel.token.observe(this
        ) { t ->
            if (t!!.status == State.Success) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else if(t.status == State.Failed) {
                if(t.message.equals("401")) {
                    startActivity(Intent(this, SignUpActivity1::class.java))
                }else {
                    Toast.makeText(this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                    mGoogleSignInClient.signOut()
                }
            }
        }
        binding.buttonLoginGoogleSignIn.setOnClickListener {
            signIn()
        }
    }
    private fun initGsc(){
        auth = FirebaseAuth.getInstance()
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .requestId()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleSignInResult(task)
            }
        }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>){
        try {
            CoroutineScope(Dispatchers.IO).launch{
                val token = task.result.idToken.toString()
                Log.d(TAG, "handleSignInResult: $token")
                val sharedPreferences = getSharedPreferences("sharedPreference", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("idToken", token)
                editor.apply()
                viewModel.login(token, sharedPreferences)
            }
        } catch (e: ApiException) {
            Log.d(TAG, "api Exception")
        }
    }

}