package com.example.dudeulimproject.view.sign_up

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.dudeulimproject.NavigationFragment
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseFragment
import com.example.dudeulimproject.databinding.FragmentSignUp1Binding
import com.example.dudeulimproject.view.sign_up.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment1: BaseFragment<FragmentSignUp1Binding>(R.layout.fragment_sign_up1){
    private val viewModel : SignUpViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.fragment = this
        binding.lifecycleOwner = this
    }
}