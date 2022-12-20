package com.example.dudeulimproject.view.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.dudeulimproject.view.create_inter_view.CreateInterViewActivity
import com.example.dudeulimproject.view.main.viewmodel.MainViewModel
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseActivity
import com.example.dudeulimproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.activity = this
        binding.lifecycleOwner = this
    }
    fun clickFabButton(view: View) {
        startActivity(Intent(this, CreateInterViewActivity::class.java))
    }
}