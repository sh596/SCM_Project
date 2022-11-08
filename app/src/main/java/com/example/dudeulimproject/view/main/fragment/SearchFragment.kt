package com.example.dudeulimproject.view.main.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseFragment
import com.example.dudeulimproject.databinding.FragmentSearchBinding
import com.example.dudeulimproject.view.search_result.SearchResultActivity
import com.example.dudeulimproject.view.explore_inter_view.adapter.FieldSpinnerAdapter
import com.example.dudeulimproject.view.main.viewmodel.SearchFragmentViewModel

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val viewModel by viewModels<SearchFragmentViewModel>()
    private lateinit var fieldItems : Array<String>
    private lateinit var coinItems : Array<String>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.fragment = this
        binding.lifecycleOwner = this
        fieldItems = resources.getStringArray(R.array.field_array)
        coinItems = resources.getStringArray(R.array.coin_array)

        val fieldAdapter = FieldSpinnerAdapter(binding.root.context, fieldItems.toList())
        val coinAdapter = FieldSpinnerAdapter(binding.root.context, coinItems.toList())

        binding.spinnerSearchFragmentField.adapter = fieldAdapter
        binding.spinnerSearchFragmentCoin.adapter = coinAdapter

        binding.editSearchFragmentSearchBox.setOnEditorActionListener {_, p1, _ ->
            when (p1) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    clickSearch(binding.root)
                }
            }
            true
        }

    }

    fun clickSearch(view: View) {
        val intent = Intent(binding.root.context, SearchResultActivity::class.java)
        intent.putExtra("searchText", viewModel.searchText.value)
        intent.putExtra("category", viewModel.category.value)
        intent.putExtra("coin", coinItems[viewModel.field.value!!])
        intent.putExtra("field", fieldItems[viewModel.coin.value!!])
        startActivity(intent)
    }
}