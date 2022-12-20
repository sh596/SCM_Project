package com.example.dudeulimproject.view.main.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dudeulimproject.data.User
import com.example.dudeulimproject.data.RequestInterViewData
import com.example.dudeulimproject.view.main.repository.ProfileRepository
import com.example.dudeulimproject.view.main.repository.ProfileRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: ProfileRepositoryImpl) : ViewModel(){
    private val _user = MutableLiveData<User>()
    val user : LiveData<User>
        get() = _user
    val completeInterViewList = ObservableArrayList<RequestInterViewData>()

    init {
        try {
            getMyProfile()
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getMyProfile () {
        viewModelScope.launch {
            val response = repository.getMyProfile()
            if(response.isSuccessful) {
                val userData = response.body()!!
                _user.postValue(User(userData.description, userData.email, userData.id, userData.image, userData.job, userData.name))
            }
        }
    }




}