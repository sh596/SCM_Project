package com.example.dudeulimproject.view.main.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dudeulimproject.data.RequestInterViewData
import com.example.dudeulimproject.data.response.RequestInterViewResponse
import com.example.dudeulimproject.utils.Resource
import com.example.dudeulimproject.utils.State
import com.example.dudeulimproject.view.main.repository.RequestFragmentRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterViewViewModel @Inject constructor(private val repository: RequestFragmentRepositoryImpl)  : ViewModel() {
    private val _requestInterViewResponse = MutableLiveData<Resource<List<RequestInterViewResponse>>>(Resource.loading())
    val requestInterViewResponse : MutableLiveData<Resource<List<RequestInterViewResponse>>>
        get() = _requestInterViewResponse
    val proceedInterViewList = ObservableArrayList<RequestInterViewData>()
    val completeInterViewList = ObservableArrayList<RequestInterViewData>()

    val refresh : () -> Unit = {
        if(requestInterViewResponse.value!!.status != State.Loading) {
            proceedInterViewList.clear()
            completeInterViewList.clear()
            requestInterViewResponse.value = Resource.loading()
            getRequestInterView()
        }
    }
    init {
        getRequestInterView()
    }

    private fun getRequestInterView() {
        viewModelScope.launch {
            val response = repository.getRequestInterView()
            if(response.isSuccessful) {
                _requestInterViewResponse.postValue(Resource.success(response.body()))
                val list = mutableListOf<RequestInterViewData>()
                for(item in response.body()!!) {
                    list.add(RequestInterViewData(item.id, item.thumbnail,
                        item.date.toString(), item.title, item.category, item.completed))
                }
                proceedInterViewList.addAll(list)
            }else {
                _requestInterViewResponse.postValue(Resource.failed(response.code().toString()))
            }
        }
    }
}
