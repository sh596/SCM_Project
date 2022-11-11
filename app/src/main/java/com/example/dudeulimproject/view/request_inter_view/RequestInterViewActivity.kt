package com.example.dudeulimproject.view.request_inter_view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager.TAG
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseActivity
import com.example.dudeulimproject.databinding.ActivityRequestInterViewBinding
import com.example.dudeulimproject.view.chat.ChatActivity
import com.example.dudeulimproject.view.request_inter_view.adapter.QuestionAdapter
import com.example.dudeulimproject.view.request_inter_view.viewmodel.RequestInterViewViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestInterViewActivity : BaseActivity<ActivityRequestInterViewBinding>(R.layout.activity_request_inter_view) {
    private val viewModel by viewModels<RequestInterViewViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.activity = this
        binding.lifecycleOwner = this
        binding.recyclerRequestInterViewQuestion.adapter = QuestionAdapter()


    }
    fun clickBackButton(view: View){
        finish()
    }
    fun clickChatButton(view: View) {
        val intent = Intent(this, ChatActivity::class.java)
        startActivity(intent)
    }
}