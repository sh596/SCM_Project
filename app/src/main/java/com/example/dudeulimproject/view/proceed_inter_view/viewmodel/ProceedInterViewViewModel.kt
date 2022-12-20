package com.example.dudeulimproject.view.proceed_inter_view.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dudeulimproject.data.RequestInterViewSeeMoreData
import com.example.dudeulimproject.utils.DummyData.DUMMY_PROCEED_INTERVIEW_SEE_MORE_1
import com.example.dudeulimproject.utils.DummyData.DUMMY_PROCEED_INTERVIEW_SEE_MORE_2
import com.example.dudeulimproject.utils.DummyData.DUMMY_REQUEST_INTERVIEW_SEE_MORE_1
import com.example.dudeulimproject.view.proceed_inter_view.repository.ProceedInterViewRepository
import com.example.dudeulimproject.view.request_inter_view.repository.RequestInterViewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProceedInterViewViewModel @Inject constructor(private val repository: RequestInterViewRepository
,private val proceedRepository: ProceedInterViewRepository) :
    ViewModel() {

    private val _interView = MutableLiveData<RequestInterViewSeeMoreData>()
    val interView: LiveData<RequestInterViewSeeMoreData>
        get() = _interView
    val audioList = ObservableArrayList<String>()

    private val _isRequester = MutableLiveData<Boolean>(false)
    val isRequester: LiveData<Boolean>
        get() = _isRequester

    fun getRequestInterView(id: String) {
        if(id == "001"){
            _interView.value = DUMMY_PROCEED_INTERVIEW_SEE_MORE_1
        }else {
            _interView.value = DUMMY_PROCEED_INTERVIEW_SEE_MORE_2
        }
    }
    fun addAudio(uri: String) {
        audioList.addAll(mutableListOf(uri))
        proceedRepository.updateAudio()
    }

    fun checkRequester(value : Boolean) {
        _isRequester.value = value
    }
}