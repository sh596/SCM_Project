package com.example.dudeulimproject.view.explore_inter_view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dudeulimproject.data.request.RequestRequest
import com.example.dudeulimproject.data.response.InterViewSeeMoreResponse
import com.example.dudeulimproject.view.explore_inter_view.repository.ExploreInterViewRepositoryImpl
import com.example.dudeulimproject.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreInterViewViewModel @Inject constructor(private val repository: ExploreInterViewRepositoryImpl) :
    ViewModel() {
    private val _result = MutableLiveData<Resource<InterViewSeeMoreResponse>>(Resource.loading())
    val result: LiveData<Resource<InterViewSeeMoreResponse>>
        get() = _result
    private val _interViewData = MutableLiveData<InterViewSeeMoreResponse>()
    val interViewData : LiveData<InterViewSeeMoreResponse>
        get() = _interViewData

    fun getExploreInterView(interViewId: String) {
        viewModelScope.launch {
            val response = repository.getExploreInterView(interViewId)
            if(response.isSuccessful) {
                _result.postValue(Resource.success(response.body()))
                _interViewData.postValue(response.body())
            }else{
                _result.postValue(Resource.failed(response.code().toString()))
            }

        }
    }

    suspend fun requestInterView(id :String) {
        val data = interViewData.value!!
        repository.requestInterView(id, RequestRequest(data.category, "", "", data.thumbnail, data.title))

    }
}