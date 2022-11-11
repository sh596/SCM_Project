package com.example.dudeulimproject.view.main.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseFragment
import com.example.dudeulimproject.databinding.FragmentMainBinding
import com.example.dudeulimproject.view.main.adapter.ExploreInterViewAdapter
import com.example.dudeulimproject.view.main.viewmodel.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    private val viewModel: MainFragmentViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.recyclerMainFragmentPopularContents.adapter = ExploreInterViewAdapter()
    }
}