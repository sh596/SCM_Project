package com.example.dudeulimproject.view.main.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import com.example.dudeulimproject.view.main.model.ExploreInterViewData

class MainFragmentViewModel : ViewModel() {
    val popularInterViewList = ObservableArrayList<ExploreInterViewData>()

    val refresh : () -> Unit = {
        popularInterViewList.clear()
        popularInterViewList.add(ExploreInterViewData(0, "", "김병주", "목탁 디자이너", 100))
        popularInterViewList.add(ExploreInterViewData(0, "", "김병주", "목탁 디자이너", 100))
    }

    init {
        refresh()
    }

}