package com.example.dudeulimproject.view.main.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dudeulimproject.data.RequestInterViewData

class InterViewViewModel : ViewModel() {
    val proceedInterViewList = ObservableArrayList<RequestInterViewData>()
    val completeInterViewList = ObservableArrayList<RequestInterViewData>()
    init {
        proceedInterViewList.add(RequestInterViewData(0, "", "", "목탁 디자이너", "온라인", false))
        proceedInterViewList.add(RequestInterViewData(0, "", "10/27 10:00", "목탁 디자이너", "온라인", false))
        completeInterViewList.add(RequestInterViewData(0, "", "10/27 10:00", "목탁 디자이너", "온라인", false))
        completeInterViewList.add(RequestInterViewData(0, "", "10/27 10:00", "목탁 디자이너", "온라인", false))
    }
}
