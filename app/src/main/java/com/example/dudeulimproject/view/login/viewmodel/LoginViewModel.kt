package com.example.dudeulimproject.view.login.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dudeulimproject.data.request.LoginRequest
import com.example.dudeulimproject.data.response.TokenResponse
import com.example.dudeulimproject.utils.Constants
import com.example.dudeulimproject.utils.Resource
import com.example.dudeulimproject.view.login.repository.LoginRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepositoryImpl) :
    ViewModel() {
    private val _token = MutableLiveData<Resource<TokenResponse>>(Resource.loading())
    val token: LiveData<Resource<TokenResponse>>
        get() = _token

    fun login(token: String, sharedPreferences: SharedPreferences) {
        val editor = sharedPreferences.edit()
        viewModelScope.launch {
            val response = repository.login(LoginRequest(token))
            val body = response.body()
            if (response.isSuccessful) {
                _token.postValue(Resource.success(body))
                editor.putString("accessToken", response.body()!!.accessToken)
                editor.apply()
                Constants.ACCESS_TOKEN = "Bearer ${response.body()!!.accessToken}"
            } else {
                _token.postValue(Resource.failed(response.code().toString()))
            }
        }
    }


}