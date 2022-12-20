package com.example.dudeulimproject.view.sign_up

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.dudeulimproject.NavigationFragment
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseFragment
import com.example.dudeulimproject.databinding.FragmentSignUp3Binding
import com.example.dudeulimproject.utils.State
import com.example.dudeulimproject.view.explore_inter_view.adapter.FieldSpinnerAdapter
import com.example.dudeulimproject.view.main.MainActivity
import com.example.dudeulimproject.view.sign_up.viewmodel.SignUpViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class SignUpFragment3 : BaseFragment<FragmentSignUp3Binding>(R.layout.fragment_sign_up_3),
    NavigationFragment {
    private val viewModel : SignUpViewModel by activityViewModels()
    private var isLoading : Boolean = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.token.observe(viewLifecycleOwner
        ) { t ->
            if (t!!.status == State.Success) {
                startActivity(Intent(binding.root.context, MainActivity::class.java))
            }else if(t.status == State.Failed) {
                Toast.makeText(binding.root.context, "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
                val mGoogleSignInClient = this.let { GoogleSignIn.getClient(binding.root.context, gso) }
                mGoogleSignInClient.signOut()
            }
        }
        bindSpinnerAdapter()

    }
    private fun bindSpinnerAdapter() {
        val fieldItems = resources.getStringArray(R.array.field_array)
        val fieldAdapter = FieldSpinnerAdapter(binding.root.context, fieldItems.toList())
        binding.spinnerSignUpJob.adapter = fieldAdapter

    }

    override fun clickNextButton() {
        viewModel.signUp()
    }

    override fun clickBackButton() {
        Navigation.findNavController(binding.root)
            .navigate(R.id.actionPopSignUpFragment2)
    }
}