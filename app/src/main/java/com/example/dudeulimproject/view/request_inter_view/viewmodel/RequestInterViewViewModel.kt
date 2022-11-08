package com.example.dudeulimproject.view.request_inter_view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dudeulimproject.UserData
import com.example.dudeulimproject.data.RequestInterViewSeeMoreData

class RequestInterViewViewModel : ViewModel() {
    private val _interView = MutableLiveData<RequestInterViewSeeMoreData>()
    val interView: LiveData<RequestInterViewSeeMoreData>
        get() = _interView

    init {
        _interView.value =
            RequestInterViewSeeMoreData(0,
                "목탁 디자이너",
                "10/27 10:00",
                "선린인터넷고등학교",
                "온라인",
                UserData(0, "김병주", "", "의사", "나다", true),
                listOf("1. 질문 1", "2. 질문 2")
            )

    }

}