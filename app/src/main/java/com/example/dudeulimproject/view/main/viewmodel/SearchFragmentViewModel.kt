package com.example.dudeulimproject.view.main.viewmodel

import android.util.Log
import androidx.fragment.app.FragmentManager.TAG
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchFragmentViewModel : ViewModel(){
    val searchText = MutableLiveData<String>()
    private val _category = MutableLiveData<Int>()
    val category : LiveData<Int>
        get() = _category
    private val _field = MutableLiveData<Int>()
    val field : LiveData<Int>
        get() = _field
    private val _coin = MutableLiveData<Int>()
    val coin : LiveData<Int>
        get() = _coin

    val setField : (Int) -> Unit = {
        _field.value = it
    }
    val setCoin : (Int) -> Unit = {
        _coin.value = it
    }
    init {
        _category.value = 0
    }

    fun setCategory(position: Int){
        _category.value = position
    }

}
