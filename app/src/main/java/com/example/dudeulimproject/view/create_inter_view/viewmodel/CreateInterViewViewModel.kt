package com.example.dudeulimproject.view.create_inter_view.viewmodel

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dudeulimproject.data.request.InterviewPostRequest
import com.example.dudeulimproject.utils.Resource
import com.example.dudeulimproject.utils.State
import com.example.dudeulimproject.view.create_inter_view.repository.CreateInterViewRepository
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CreateInterViewViewModel @Inject constructor(private val repository: CreateInterViewRepository) :
    ViewModel() {

    private var _position = MutableLiveData<Int>(0)
    val position: LiveData<Int>
        get() = _position
    private val _uri = MutableLiveData<Uri>()
    val uri: LiveData<Uri>
        get() = _uri

    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val amount = MutableLiveData<String>()
    private val _field = MutableLiveData<Int>(0)
    val field: LiveData<Int>
        get() = _field
    private val _category = MutableLiveData<Int>(0)
    val category: LiveData<Int>
        get() = _category
    private val _thumbnail = MutableLiveData<String>()
    val thumbnail: LiveData<String>
        get() = _thumbnail
    val setField: (Int) -> Unit = {
        _field.value = it
    }

    fun createInterView() {
        val categoryName = convertCategoryToString()
        viewModelScope.launch(Dispatchers.IO) {
            viewModelScope.launch(Dispatchers.IO) {
                uploadImage()
            }.join()
            val result = repository.createInterView(
                InterviewPostRequest(
                    amount.value!!.toInt(),
                    categoryName,
                    description.value.toString(),
                    field.value!!,
                    thumbnail.value.toString(),
                    title.value.toString()
                )
            )

        }
    }

    private fun uploadImage() {
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val uuid = UUID.randomUUID()
        _thumbnail.postValue(uuid.toString())
        val fileName = "$uuid.jpg"
        val riversRef = storageRef.child("/$fileName")
        riversRef.putFile(uri.value!!)
    }

    fun setUri(uri: Uri) {
        _uri.value = uri
    }

    fun setCategory(position: Int) {
        _category.value = position
    }

    private fun convertCategoryToString(): String {
        return when (category.value) {
            0 -> {
                "online"
            }
            1 -> {
                "offline"
            }
            else -> {
                "online"
            }
        }
    }

    fun setFragmentPosition(position: Int) {
        _position.value = position
    }
}
