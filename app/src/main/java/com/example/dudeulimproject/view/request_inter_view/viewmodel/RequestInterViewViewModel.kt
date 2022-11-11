package com.example.dudeulimproject.view.request_inter_view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dudeulimproject.data.RequestInterViewSeeMoreData
import com.example.dudeulimproject.data.User
import com.example.dudeulimproject.view.main.repository.RequestFragmentRepositoryImpl
import com.example.dudeulimproject.view.request_inter_view.repository.RequestInterViewRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestInterViewViewModel @Inject constructor(private val repository: RequestInterViewRepositoryImpl) :
    ViewModel() {
    private val _interView = MutableLiveData<RequestInterViewSeeMoreData>()
    val interView: LiveData<RequestInterViewSeeMoreData>
        get() = _interView

    init {
        getRequestInterView()
    }
    private fun getRequestInterView() {
        _interView.value = RequestInterViewSeeMoreData(
            0,
            "",
            "12/10 10:00",
            "zoom",
            "온라인",
            User("", "", "", "", "개발자", "박금혁"),
            listOf()
        )
    }
}