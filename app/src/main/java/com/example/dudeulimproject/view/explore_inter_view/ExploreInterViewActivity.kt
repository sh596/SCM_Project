package com.example.dudeulimproject.view.explore_inter_view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseActivity
import com.example.dudeulimproject.databinding.ActivityExploreInterViewBinding
import com.example.dudeulimproject.view.explore_inter_view.adapter.ReviewAdapter
import com.example.dudeulimproject.view.explore_inter_view.viewmodel.ExploreInterViewViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExploreInterViewActivity : BaseActivity<ActivityExploreInterViewBinding>(R.layout.activity_explore_inter_view) {
    private val viewModel by viewModels<ExploreInterViewViewModel>()
    private var interViewId : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.activity = this
        binding.lifecycleOwner = this
        binding.recyclerInterViewReview.adapter = ReviewAdapter()

        interViewId = intent.getStringExtra("interViewId").toString()
        getExploreInterView()
    }

    private fun getExploreInterView() {
        viewModel.getExploreInterView(interViewId.toString())
    }

    fun clickBackButton(view: View) {
        finish()
    }

    fun clickRequestButton(view: View) {
        CoroutineScope(Dispatchers.IO).launch{
            viewModel.requestInterView(interViewId.toString())
            finish()
        }
    }

}

