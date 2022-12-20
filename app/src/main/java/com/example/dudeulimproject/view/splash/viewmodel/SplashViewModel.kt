package com.example.dudeulimproject.view.splash.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dudeulimproject.utils.Resource
import com.example.dudeulimproject.view.splash.repository.SplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: SplashRepository,private val preferences: SharedPreferences) : ViewModel() {
    private val _checkResult = MutableLiveData<Resource<String>>(Resource.loading())
    val checkResult : LiveData<Resource<String>>
            get() = _checkResult

    fun checkLogin() {
        viewModelScope.launch {
            val result = repository.checkLogin()
            if(result.isSuccessful) {
                _checkResult.postValue(Resource.success(result.body()))
            }else{
                _checkResult.postValue(Resource.failed("access token inconsistency"))
            }
        }
    }
}