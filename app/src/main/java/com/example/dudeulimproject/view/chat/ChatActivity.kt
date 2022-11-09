package com.example.dudeulimproject.view.chat

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearSmoothScroller
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseActivity
import com.example.dudeulimproject.databinding.ActivityChatBinding
import com.example.dudeulimproject.view.chat.adapter.ChatAdapter
import com.example.dudeulimproject.view.chat.viewmodel.ChatViewModel
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChatActivity : BaseActivity<ActivityChatBinding>(R.layout.activity_chat) {
    private val firebaseDB = FirebaseDatabase.getInstance()

    private val viewModel by viewModels<ChatViewModel> ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.activity = this
        binding.lifecycleOwner = this

        binding.recyclerChat.adapter = ChatAdapter("001")
        viewModel.initChat("001")

    }
    fun clickBackButton(view: View) {
        finish()
    }
    fun clickSendButton(view : View){
        viewModel.sendChat()
        binding.editSearchFragmentSearchBox.text.clear()
    }
}