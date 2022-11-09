package com.example.dudeulimproject.view.chat

import android.content.ContentValues
import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.example.dudeulimproject.data.ChatData
import com.example.dudeulimproject.view.chat.adapter.ChatAdapter

object ChatBindingAdapter {
    @BindingAdapter("bindChatList")
    @JvmStatic
    fun bindChatList(view: RecyclerView, chatList: ObservableArrayList<ChatData>?){
        val adapter = view.adapter as ChatAdapter
        if(chatList != null) {
            adapter.submitList(chatList.toMutableList())
            view.smoothScrollToPosition(chatList.size)
        }

    }
}