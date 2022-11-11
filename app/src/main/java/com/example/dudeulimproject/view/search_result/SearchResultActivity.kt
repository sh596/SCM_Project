package com.example.dudeulimproject.view.search_result

import android.os.Bundle
import androidx.activity.viewModels
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseActivity
import com.example.dudeulimproject.databinding.ActivitySearchResultBinding
import com.example.dudeulimproject.view.main.adapter.ExploreInterViewAdapter
import com.example.dudeulimproject.view.search_result.viewModel.SearchResultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        val field = intent.getIntExtra("field", 0)
        val coin = intent.getIntExtra("coin", 0)

        val categoryText = when (category) {
            0 -> "online"
            1 -> "offline"
            else -> "any"
        }
        searchInterView(categoryText, field, coin, searchText)

        binding.textSearchResultCategory.text = if (category == 0) {
            "온라인"
        } else {
            "오프라인"
        }
        binding.textSearchResultField.text = resources.getStringArray(R.array.field_array)[field]
        binding.textSearchResultCoin.text = resources.getStringArray(R.array.coin_array)[coin]
        binding.textSearchResultFragmentResultTitle.text = if (searchText == null) {
            "\"\"의 검색 결과"
        } else {
            "\"$searchText\"의 검색 결과"
        }
    }

    private fun searchInterView(category: String, field: Int, coin: Int, searchText: String?) {
        viewModel.searchInterView(category, field, coin, searchText)
    }
}