package com.example.dudeulimproject.view.search_result.viewModel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dudeulimproject.data.ExploreInterViewData
import com.example.dudeulimproject.data.response.InterViewResponse
import com.example.dudeulimproject.utils.Resource
import com.example.dudeulimproject.view.search_result.repository.SearchResultRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(private val repository : SearchResultRepositoryImpl) : ViewModel() {
    private val _interViewList = MutableLiveData<Resource<List<InterViewResponse>>>(Resource.loading())
    val interViewList : LiveData<Resource<List<InterViewResponse>>>
        get() = _interViewList
    val exploreInterViewList = ObservableArrayList<ExploreInterViewData>()

    fun searchInterView(category: String, field: Int, coin : Int, searchText: String?) {
        val amountFrom = when(coin) {
            0 -> 100
            1 -> 500
            2 ->  1000
            3 -> 1500
            4 -> 2000
            else -> 100
        }
        val amountTo = when(coin) {
            0 -> 500
            1 -> 1000
            2 ->  1500
            3 -> 2000
            4 -> 50000
            else -> 500
        }
        viewModelScope.launch {
            val response = repository.getSearchInterview(category, field, amountFrom, amountTo, searchText)
            if(response.isSuccessful) {
                _interViewList.postValue(Resource.success(response.body()))
                val list = mutableListOf<ExploreInterViewData>()
                for(item in response.body()!!){
                    list.add(ExploreInterViewData(item.id,item.thumbnail, item.user.name, item.title, item.amount))
                }
                exploreInterViewList.addAll(list)
            }else {
                _interViewList.postValue(Resource.failed(response.code().toString()))
            }
        }

    }
}