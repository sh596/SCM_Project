package com.example.dudeulimproject.view.search_result.viewModel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.example.dudeulimproject.data.ExploreInterViewData

class SearchResultViewModel : ViewModel() {
    val exploreInterViewList = ObservableArrayList<ExploreInterViewData>()

    init {
        repeat(5) {
            exploreInterViewList.add(ExploreInterViewData(0, "", "김병주", "목탁 디자이너", 100))
        }
    }
}