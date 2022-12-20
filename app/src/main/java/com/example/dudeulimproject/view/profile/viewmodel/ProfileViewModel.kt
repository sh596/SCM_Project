package com.example.dudeulimproject.view.profile.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dudeulimproject.data.RequestInterViewData
import com.example.dudeulimproject.data.User
import com.example.dudeulimproject.utils.Resource
import com.example.dudeulimproject.view.main.repository.ProfileRepository
import com.example.dudeulimproject.view.profile.repository.OtherProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: OtherProfileRepository) :
    ViewModel() {
    private val _user = MutableLiveData<Resource<User>>(Resource.loading())
    val user: LiveData<Resource<User>>
        get() = _user
    val completeInterViewList = ObservableArrayList<RequestInterViewData>()

    fun getProfile(id: String) {
        viewModelScope.launch {
            val response = repository.getProfile(id)
            if (response.isSuccessful) {
                val userData = response.body()!!
                _user.postValue(
                    Resource.success(
                        User(
                            userData.description,
                            userData.email,
                            userData.id,
                            userData.image,
                            userData.job,
                            userData.name
                        )
                    )
                )
            } else {
                _user.postValue(Resource.failed("user not found"))
            }
        }
    }

}