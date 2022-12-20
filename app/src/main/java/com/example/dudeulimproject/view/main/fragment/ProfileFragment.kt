package com.example.dudeulimproject.view.main.fragment

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseFragment
import com.example.dudeulimproject.databinding.FragmentInterviewBinding
import com.example.dudeulimproject.databinding.FragmentProfileBinding
import com.example.dudeulimproject.view.login.LoginActivity
import com.example.dudeulimproject.view.main.viewmodel.ProfileViewModel
import com.example.dudeulimproject.view.profile_edit.ProfileEditActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment :  BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.fragment = this
        binding.lifecycleOwner = this
    }
    fun signOut(view: View) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()

        val mGoogleSignInClient = this.let { GoogleSignIn.getClient(requireActivity(), gso) }
        mGoogleSignInClient.signOut()
        val editor = requireActivity().getSharedPreferences("sharedPreference",MODE_PRIVATE).edit()
        editor.putString("accessToken", "")
        editor.commit()
        startActivity(Intent(context,LoginActivity::class.java))
        requireActivity().finish()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getMyProfile()
    }

    fun clickEditProfileButton(view: View){
        startActivity(Intent(binding.root.context, ProfileEditActivity::class.java))
    }
}