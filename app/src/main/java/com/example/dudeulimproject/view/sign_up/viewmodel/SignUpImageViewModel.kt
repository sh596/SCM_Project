package com.example.dudeulimproject.view.sign_up.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dudeulimproject.data.request.RegistRequest
import com.example.dudeulimproject.data.response.TokenResponse
import com.example.dudeulimproject.utils.Resource
import com.example.dudeulimproject.view.sign_up.repository.SignUpRepository
import com.example.dudeulimproject.view.sign_up.repository.SignUpRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpImageViewModel @Inject constructor(private val repository: SignUpRepositoryImpl) : ViewModel() {
    private val _token = MutableLiveData<Resource<TokenResponse>>(Resource.loading())
    val token : LiveData<Resource<TokenResponse>>
        get() = _token
    private val _imageUUID = MutableLiveData<String>()
    val imageUUID : LiveData<String>
        get() = _imageUUID

    init {
        _imageUUID.value = ""
    }

    fun signUp(token: String, nickname: String,sharedPreferences: SharedPreferences) {
        val editor = sharedPreferences.edit()
        viewModelScope.launch {
            val response = repository.singUp(RegistRequest(token, nickname, imageUUID.value!!))
            if(response.isSuccessful) {
                _token.postValue(Resource.success(response.body()))
                editor.putString("accessToken", token)
                editor.apply()
            }else {
                _token.postValue(Resource.failed(response.code().toString()))
            }
        }
    }
    fun setUUID(uuid: String) {
        _imageUUID.value = uuid
    }
}