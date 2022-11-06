package com.example.dudeulimproject.view.inter_view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseActivity
import com.example.dudeulimproject.databinding.ActivityInterViewBinding
import com.example.dudeulimproject.view.inter_view.adapter.ReviewAdapter
import com.example.dudeulimproject.view.inter_view.viewmodel.InterViewViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InterViewActivity : BaseActivity<ActivityInterViewBinding>(R.layout.activity_inter_view) {
    private val viewModel :InterViewViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.activity = this
        binding.recyclerInterViewReview.adapter = ReviewAdapter()
    }

    fun clickBackButton(view: View) {
        finish()
    }

    fun clickRequestButton(view: View) {
        CoroutineScope(Dispatchers.IO).launch{
            viewModel.requestInterView()
            finish()
        }
    }

}

