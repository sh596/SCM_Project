package com.example.dudeulimproject.view.request_inter_view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dudeulimproject.data.RequestInterViewSeeMoreData
import com.example.dudeulimproject.utils.DummyData.DUMMY_REQUEST_INTERVIEW_SEE_MORE_1
import com.example.dudeulimproject.utils.DummyData.DUMMY_REQUEST_INTERVIEW_SEE_MORE_2
import com.example.dudeulimproject.view.main.repository.ProfileRepository
import com.example.dudeulimproject.view.request_inter_view.repository.RequestInterViewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestInterViewViewModel @Inject constructor(
    private val interViewRepository: RequestInterViewRepository,
    private val profileRepository: ProfileRepository
) :
    ViewModel() {
    private val _interView = MutableLiveData<RequestInterViewSeeMoreData>()
    val interView: LiveData<RequestInterViewSeeMoreData>
        get() = _interView
    private val _isRequester = MutableLiveData<Boolean>(false)
    val isRequester: LiveData<Boolean>
        get() = _isRequester


    fun getRequestInterView(id: String) {
        if (id == "001") {
            _interView.value = DUMMY_REQUEST_INTERVIEW_SEE_MORE_1
        } else {
            _interView.value = DUMMY_REQUEST_INTERVIEW_SEE_MORE_2
        }
    }

    fun checkRequester(value : Boolean) {
        _isRequester.value = value
    }
}