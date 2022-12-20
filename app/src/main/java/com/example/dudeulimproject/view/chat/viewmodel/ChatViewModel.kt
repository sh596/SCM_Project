package com.example.dudeulimproject.view.chat.viewmodel

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.FragmentManager.TAG
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dudeulimproject.data.ChatData
import com.example.dudeulimproject.view.chat.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val repository: ChatRepository) : ViewModel() {
    val chattingText = MutableLiveData<String>()
    val chatList = ObservableArrayList<ChatData>()

    fun initChat(id: String) {
        repository.subscribeMessages(chatList, id)
    }

    fun sendChat(userId: String, roomId: String) {
        viewModelScope.launch {
            if(!chattingText.value.isNullOrBlank()) {
                repository.sendMessage(ChatData(userId, roomId, chattingText.value!!, System.currentTimeMillis()))
            }
        }
    }
}