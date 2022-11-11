package com.example.dudeulimproject.view.main.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dudeulimproject.data.ExploreInterViewData
import com.example.dudeulimproject.data.response.InterViewResponse
import com.example.dudeulimproject.data.response.InterViewSeeMoreResponse
import com.example.dudeulimproject.utils.Resource
import com.example.dudeulimproject.utils.State
import com.example.dudeulimproject.view.main.repository.MainFragmentRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainFragmentViewModel @Inject constructor(private val repository: MainFragmentRepositoryImpl): ViewModel() {
    private val _interViewListResponse = MutableLiveData<Resource<List<InterViewResponse>>>(Resource.loading())
    val interviewListResponse : LiveData<Resource<List<InterViewResponse>>>
        get() = _interViewListResponse
    val popularInterViewList = ObservableArrayList<ExploreInterViewData>()

    val refresh : () -> Unit = {
        if(interviewListResponse.value!!.status != State.Loading) {
            popularInterViewList.clear()
            _interViewListResponse.value = Resource.loading()
            getInterViewList()
        }
    }

    init {
        getInterViewList()
    }

    private fun getInterViewList() {
        viewModelScope.launch {
            val response = repository.getExploreInterViewList()
            if(response.isSuccessful) {
                _interViewListResponse.postValue(Resource.success(response.body()))
                val list = mutableListOf<ExploreInterViewData>()
                for(item in response.body()!!){
                    list.add(ExploreInterViewData(item.id,item.thumbnail, item.user.name, item.title, item.amount))
                }
                popularInterViewList.addAll(list)
            }else {
                _interViewListResponse.postValue(Resource.failed(response.code().toString()))
            }
        }
    }

}