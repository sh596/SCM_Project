package com.example.dudeulimproject.view.sign_up.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dudeulimproject.data.request.RegistRequest
import com.example.dudeulimproject.utils.Resource
import com.example.dudeulimproject.view.sign_up.repository.SignUpRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpNicknameViewModel @Inject constructor(private val repository: SignUpRepositoryImpl) : ViewModel() {
    val nickname = MutableLiveData<String>()

}