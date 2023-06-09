package com.example.dudeulimproject.view.chat.repository

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.FragmentManager.TAG
import com.example.dudeulimproject.data.ChatData
import com.example.dudeulimproject.data.ChatRoomData
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.tasks.await
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ChatRepositoryImpl : ChatRepository {
    override suspend fun sendMessage(chatData: ChatData): DocumentReference? {
        Log.d(TAG, "sendMessage: ${chatData.InterViewId}")
        return Firebase.firestore
            .collection("chatRoom")
            .document(chatData.InterViewId)
            .collection("Chat")
            .add(chatData)
            .await()
    }

    override fun subscribeMessages(list: ObservableArrayList<ChatData>, roomId: String) {
        Firebase.firestore
            .collection("chatRoom")
            .document(roomId)
            .collection("Chat")
            .orderBy("date")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val messages = mutableListOf<ChatRoomData>()
                    snapshot.documentChanges.forEach {
                        if (it.type == DocumentChange.Type.ADDED) {
                            list.add(it.document.toObject())
                        } else if (it.type == DocumentChange.Type.MODIFIED) {
                            list.add(it.document.toObject())
                        }
                    }
                }
            }
    }

    override suspend fun receiveChatData(roomId: String): QuerySnapshot {
        return Firebase.firestore
            .collection("chatRoom")
            .document(roomId)
            .collection("Chat")
            .orderBy("date")
            .get()
            .await()

    }
}