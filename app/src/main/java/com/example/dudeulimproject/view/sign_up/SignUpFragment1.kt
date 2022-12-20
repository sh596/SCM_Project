package com.example.dudeulimproject.view.sign_up

import android.content.SharedPreferences
import android.os.Bundle
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
class SignUpFragment1 @Inject constructor(private val preferences: SharedPreferences): BaseFragment<FragmentSignUp1Binding>(R.layout.fragment_sign_up1),
    NavigationFragment {
    private val viewModel : SignUpViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.activity = this
        binding.lifecycleOwner = this
    }

    override fun clickBackButton() {
        activity?.finish()
    }

    override fun clickNextButton() {
        if( !(viewModel.nickname.value.isNullOrBlank()) ||viewModel.nickname.value!!.length in 4..12 ) {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_signUpFragment1_to_signUpFragment2)
        }else {
            Toast.makeText(binding.root.context, "입력 조건을 맞춰주세요", Toast.LENGTH_SHORT).show()
        }
    }

}