package com.example.dudeulimproject.view.main.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.log

class MainViewModel : ViewModel() {
    private val _bottomNavigationPosition = MutableLiveData<Int>()
    val bottomNavigationPosition : LiveData<Int>
        get() = _bottomNavigationPosition

    fun changePosition(position : Int) {
        Log.d(TAG, "changePosition: click button")
        _bottomNavigationPosition.value = position
    }


    val changePosition : (Int) -> Unit = {position -> changePosition(position)}
}