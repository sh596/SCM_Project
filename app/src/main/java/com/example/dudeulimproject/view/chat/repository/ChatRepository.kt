package com.example.dudeulimproject.view.chat.repository

import androidx.databinding.ObservableArrayList
import com.example.dudeulimproject.data.ChatData
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import dagger.Provides

interface ChatRepository {
    suspend fun sendMessage(chatData: ChatData): DocumentReference?
    fun subscribeMessages(list : ObservableArrayList<ChatData>, roomId: String)
    suspend fun receiveChatData(roomId: String): QuerySnapshot
}