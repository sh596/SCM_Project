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
import androidx.fragment.app.FragmentManager.TAG
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.dudeulimproject.NavigationFragment
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseFragment
import com.example.dudeulimproject.databinding.FragmentSignUp2Binding
import com.example.dudeulimproject.view.sign_up.viewmodel.SignUpViewModel
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
@AndroidEntryPoint
class SignUpFragment2 : BaseFragment<FragmentSignUp2Binding>(R.layout.fragment_sign_up2){
    private var isLoading = false
    private lateinit var idToken: String
    private lateinit var nickname: String
    private val viewModel : SignUpViewModel by activityViewModels()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.activity = this
        binding.lifecycleOwner = this

    }

    fun clickProfileImage(view: View) {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        resultLauncher.launch(intent)
    }


}