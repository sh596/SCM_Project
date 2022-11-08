package com.example.dudeulimproject.view.search_result

import android.os.Bundle
import androidx.activity.viewModels
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseActivity
import com.example.dudeulimproject.databinding.ActivitySearchResultBinding
import com.example.dudeulimproject.view.main.adapter.ExploreInterViewAdapter
import com.example.dudeulimproject.view.search_result.viewModel.SearchResultViewModel

class SearchResultActivity :
    BaseActivity<ActivitySearchResultBinding>(R.layout.activity_search_result) {
    private val viewModel by viewModels<SearchResultViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.recyclerSearchResultExploreInterView.adapter = ExploreInterViewAdapter()
        val searchText = intent.getStringExtra("searchText")
        val category = intent.getIntExtra("category", 0)
        val field = intent.getStringExtra("field")
        val coin = intent.getStringExtra("coin")

        binding.textSearchResultCategory.text = if (category == 0) {
            "온라인"
        } else {
            "오프라인"
        }
        binding.textSearchResultField.text = field
        binding.textSearchResultCoin.text = coin
        binding.textSearchResultFragmentResultTitle.text = "\"$searchText\"의 검색 결과"


    }
}