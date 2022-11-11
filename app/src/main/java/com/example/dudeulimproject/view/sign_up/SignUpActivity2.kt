package com.example.dudeulimproject.view.sign_up

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager.TAG
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseActivity
import com.example.dudeulimproject.databinding.ActivitySignUp2Binding
import com.example.dudeulimproject.utils.State
import com.example.dudeulimproject.view.main.MainActivity
import com.example.dudeulimproject.view.sign_up.viewmodel.SignUpImageViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
@AndroidEntryPoint
class SignUpActivity2 : BaseActivity<ActivitySignUp2Binding>(R.layout.activity_sign_up2) {
    private var isLoading = false
    private lateinit var idToken: String
    private lateinit var nickname: String
    private val viewModel by viewModels<SignUpImageViewModel>()
    private val resultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri: Uri? = result.data?.data
                if (uri != null) {
                    upload(uri)
                }
            }
        }

    @SuppressLint("RestrictedApi")
    private fun upload(uri: Uri) {
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val uuid = UUID.randomUUID()
        val fileName = "$uuid.jpg"
        val riversRef = storageRef.child("/$fileName")

        isLoading = true
        riversRef.putFile(uri)
            .addOnFailureListener {
                isLoading = false
                Toast.makeText(binding.root.context, "이미지 업로드 실패", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "upload: ${it.message}")
            }
            .addOnSuccessListener {
                viewModel.setUUID(uuid.toString())
                isLoading = false
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.activity = this
        binding.lifecycleOwner = this

        idToken = getSharedPreferences("sharedPreference", MODE_PRIVATE).getString("idToken", "").toString()
        nickname = intent.getStringExtra("nickname").toString()

        viewModel.token.observe(this
        ) { t ->
            if (t!!.status == State.Success) {
                isLoading = false
                startActivity(Intent(this, MainActivity::class.java))
                finishAffinity()
            }else if(t.status == State.Failed) {
                isLoading = false
                Toast.makeText(this, "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
                val mGoogleSignInClient = this.let { GoogleSignIn.getClient(this, gso) }
                mGoogleSignInClient.signOut()
                finishAffinity()
            }
        }

    }



    fun clickProfileImage(view: View) {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        resultLauncher.launch(intent)
    }

    fun clickBackButton(view: View) {
        finish()
    }

    fun clickNextButton(view: View) {
        if (!isLoading) {
            isLoading = true
            val sharedPreferences = getSharedPreferences("sharedPreference", MODE_PRIVATE)
            viewModel.signUp(idToken, nickname,sharedPreferences)
        }
    }
}