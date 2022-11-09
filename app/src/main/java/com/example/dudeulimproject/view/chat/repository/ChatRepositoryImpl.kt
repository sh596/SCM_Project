package com.example.dudeulimproject.view.chat.repository

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.FragmentManager.TAG
import com.example.dudeulimproject.data.ChatData
import com.example.dudeulimproject.data.ChatRoomData
import com.example.dudeulimproject.utils.FirebaseUtilCode.Companion.getFirestore
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.tasks.await
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject

class ChatRepositoryImpl : ChatRepository {
    override suspend fun sendMessage(chatData: ChatData): DocumentReference? {
        return getFirestore()
            .collection("chatRoom")
            .document(chatData.InterViewId)
            .collection("Chat")
            .add(chatData)
            .await()
    }

    override fun subscribeMessages(list: ObservableArrayList<ChatData>, roomId: String) {
        getFirestore()
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
        return getFirestore()
            .collection("chatRoom")
            .document(roomId)
            .collection("Chat")
            .orderBy("date")
            .get()
            .await()

    }
}