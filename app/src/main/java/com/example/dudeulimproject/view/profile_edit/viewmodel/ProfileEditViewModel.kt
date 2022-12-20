package com.example.dudeulimproject.view.profile_edit.viewmodel

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dudeulimproject.data.request.UpdateProfileRequest
import com.example.dudeulimproject.data.response.ProfileResponse
import com.example.dudeulimproject.utils.Resource
import com.example.dudeulimproject.utils.State
import com.example.dudeulimproject.view.main.repository.ProfileRepository
import com.example.dudeulimproject.view.profile_edit.repository.ProfileEditRepository
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import retrofit2.Response
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val profileEditRepository: ProfileEditRepository,
    private val profileRepository: ProfileRepository
) :
    ViewModel() {

    private val _uri = MutableLiveData<Uri>()
    val uri: LiveData<Uri>
        get() = _uri
    val nickname = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val job = MutableLiveData<String>()
    private val _imageUUID = MutableLiveData<String>()
    val imageUUID: LiveData<String>
        get() = _imageUUID
    private val _updateResult = MutableLiveData<Resource<Boolean>>(Resource.loading())
    val updaterResult: LiveData<Resource<Boolean>>
        get() = _updateResult

    fun editProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            viewModelScope.launch(Dispatchers.IO) {
                uploadImage()
            }.join()

            val result = profileEditRepository.updateProfile(
                UpdateProfileRequest(
                    description.value!!,
                    imageUUID.value!!,
                    job.value!!,
                    nickname.value!!
                )
            )
            if (result.isSuccessful) {
                _updateResult.postValue(Resource.success(result.body()))
            } else {
                _updateResult.postValue(Resource.failed("update profile fail"))
            }
        }
    }

    fun getProfile() {
        viewModelScope.launch {
            val result = profileRepository.getMyProfile()
            if (result.isSuccessful) {
                nickname.postValue(result.body()!!.name)
                job.postValue(result.body()!!.job.toString())
                description.postValue(result.body()!!.description.toString())
                _imageUUID.postValue(result.body()!!.image)
            }
        }
    }

    fun setUri(uri: Uri) {
        _uri.value = uri
    }

    private fun uploadImage() {
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val uuid = UUID.randomUUID()
        val fileName = "$uuid.jpg"
        _imageUUID.postValue(uuid.toString())
        val riversRef = storageRef.child("/$fileName")

        val result = riversRef.putFile(uri.value!!)
    }

}