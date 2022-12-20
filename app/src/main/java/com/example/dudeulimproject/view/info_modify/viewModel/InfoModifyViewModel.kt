package com.example.dudeulimproject.view.info_modify.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dudeulimproject.view.info_modify.repository.InfoModifyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoModifyViewModel @Inject constructor(private val repository: InfoModifyRepository) : ViewModel() {
    private val _isOnline = MutableLiveData<Boolean>()
    val isOnline : LiveData<Boolean>
        get() = _isOnline
    val place = MutableLiveData<String>()
    private val _placeData = MutableLiveData<String>()
    val placeData : LiveData<String>
        get()= _placeData

    val date = MutableLiveData<String>()

    fun setPlace(place: String) {
        initData(isOnline.value!!, place, date.value!!)
    }

    fun save() {
        viewModelScope.launch {
            val result = repository.save()
        }
    }
    fun initData(isOnline: Boolean, place: String, date: String){
        _isOnline.value = isOnline
        this.date.value = date
        this.place.value = place
        val placeList = place.split("|").toList()
        if(placeList.isNotEmpty()){
            this.place.value = placeList[0]
        }
        if(placeList.size != 1){
            this._placeData.value = placeList[1]+"|"+placeList[2]
        }
    }
}