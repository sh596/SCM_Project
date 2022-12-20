package com.example.dudeulimproject.view.main.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dudeulimproject.data.RequestInterViewData
import com.example.dudeulimproject.data.response.RequestInterViewResponse
import com.example.dudeulimproject.utils.DummyData.DUMMY_PROCEED_INTERVIEW_1
import com.example.dudeulimproject.utils.DummyData.DUMMY_PROCEED_INTERVIEW_2
import com.example.dudeulimproject.utils.DummyData.DUMMY_PROCEED_INTERVIEW_SEE_MORE_1
import com.example.dudeulimproject.utils.DummyData.DUMMY_REQUEST_INTERVIEW_1
import com.example.dudeulimproject.utils.DummyData.DUMMY_REQUEST_INTERVIEW_2
import com.example.dudeulimproject.utils.DummyData.DUMMY_USER_1
import com.example.dudeulimproject.utils.Resource
import com.example.dudeulimproject.utils.State
import com.example.dudeulimproject.view.main.repository.RequestFragmentRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterViewViewModel @Inject constructor(private val repository: RequestFragmentRepositoryImpl)  : ViewModel() {
    private val requestInterViewResponse = MutableLiveData<Resource<List<RequestInterViewResponse>>>(Resource.loading())
    val requestInterViewList = ObservableArrayList<RequestInterViewData>()
    val proceedInterViewList = ObservableArrayList<RequestInterViewData>()

    val refresh : () -> Unit = {
        if(requestInterViewResponse.value!!.status != State.Loading) {
            requestInterViewList.clear()
            proceedInterViewList.clear()
            requestInterViewResponse.value = Resource.loading()
            getDummyData()
//            getRequestInterView()
        }
    }
    init {
        getDummyData()
        getRequestInterView()
    }

    private fun getDummyData(){
        requestInterViewList.addAll(
            listOf(DUMMY_REQUEST_INTERVIEW_1, DUMMY_REQUEST_INTERVIEW_2))
        proceedInterViewList.addAll(
            listOf(
                DUMMY_PROCEED_INTERVIEW_1, DUMMY_PROCEED_INTERVIEW_2
            )
        )
    }
    private fun getRequestInterView() {
        viewModelScope.launch {
            val response = repository.getRequestInterView()
            if(response.isSuccessful) {
                requestInterViewResponse.postValue(Resource.success(response.body()))
                val list = mutableListOf<RequestInterViewData>()
                for(item in response.body()!!) {
                    list.add(RequestInterViewData(item.id, item.thumbnail,
                        item.date.toString(), item.title, item.category, true, DUMMY_USER_1))
                }
                requestInterViewList.addAll(list)
            }else {
                requestInterViewResponse.postValue(Resource.failed(response.code().toString()))
            }
        }
    }
}
