package com.example.dudeulimproject.view.sign_up.viewmodel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dudeulimproject.data.request.RegistRequest
import com.example.dudeulimproject.data.response.TokenResponse
import com.example.dudeulimproject.utils.Resource
import com.example.dudeulimproject.view.sign_up.repository.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: SignUpRepository,
    private val preferences: SharedPreferences
) : ViewModel() {
    val nickname = MutableLiveData<String>()

    private val _token = MutableLiveData<Resource<TokenResponse>>()
    val token: LiveData<Resource<TokenResponse>>
        get() = _token
    private val _imageUUID = MutableLiveData<String>()
    val imageUUID: LiveData<String>
        get() = _imageUUID

    val job = MutableLiveData<String>()

    init {
        _imageUUID.value = ""
    }


    fun signUp() {
        val editor = preferences.edit()
        viewModelScope.launch {
            _token.postValue(Resource.loading())
            val response = repository.singUp(RegistRequest(preferences.getString("idToken","").toString(), nickname.value.toString(), imageUUID.value.toString()))
            if (response.isSuccessful) {
                _token.postValue(Resource.success(response.body()))
                editor.putString("accessToken", token.value!!.data!!.accessToken).apply()
                editor.apply()
            } else {
                _token.postValue(Resource.failed(response.code().toString()))
            }
        }
    }

    fun setUUID(uuid: String) {
        _imageUUID.value = uuid
    }
}