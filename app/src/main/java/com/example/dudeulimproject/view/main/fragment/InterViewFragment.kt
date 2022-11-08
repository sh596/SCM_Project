package com.example.dudeulimproject.view.main.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseFragment
import com.example.dudeulimproject.databinding.FragmentInterviewBinding
import com.example.dudeulimproject.view.main.adapter.RequestInterViewAdapter
import com.example.dudeulimproject.view.main.viewmodel.InterViewViewModel

class InterViewFragment : BaseFragment<FragmentInterviewBinding>(R.layout.fragment_interview) {

    private val viewModel by viewModels<InterViewViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.recyclerInterViewFragmentProceed.adapter = RequestInterViewAdapter()
        binding.recyclerInterViewFragmentComplete.adapter = RequestInterViewAdapter()
    }
}