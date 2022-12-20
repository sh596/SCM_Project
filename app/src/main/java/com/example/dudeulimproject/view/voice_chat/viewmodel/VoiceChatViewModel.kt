package com.example.dudeulimproject.view.voice_chat.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VoiceChatViewModel : ViewModel(){
    private val _micEnabled = MutableLiveData<Boolean>(true)
    val micEnabled : LiveData<Boolean>
        get() = _micEnabled
    private val _webcamEnabled = MutableLiveData(true)
    val webcamEnabled: LiveData<Boolean>
        get() = _webcamEnabled
    private val _isConnect = MutableLiveData(false)
    val isConnect: LiveData<Boolean>
        get() = _isConnect

    fun changeMic() {
        _micEnabled.value = !(micEnabled.value!!)
    }
    fun changeCam() {
        _webcamEnabled.value = !(webcamEnabled.value!!)
    }
    fun disConnect(){
        _isConnect.value = false
    }
    fun connect(){
        _isConnect.value = true;
    }
}