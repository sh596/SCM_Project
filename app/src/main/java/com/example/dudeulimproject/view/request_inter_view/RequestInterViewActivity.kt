package com.example.dudeulimproject.view.request_inter_view

import android.content.ContentValues.TAG
import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.example.dudeulimproject.R
import com.example.dudeulimproject.base.BaseActivity
import com.example.dudeulimproject.databinding.ActivityRequestInterViewBinding
import com.example.dudeulimproject.view.chat.ChatActivity
import com.example.dudeulimproject.view.dialog.NormalDialog
import com.example.dudeulimproject.view.info_modify.InfoModifyActivity
import com.example.dudeulimproject.view.map.MapActivity
import com.example.dudeulimproject.view.proceed_inter_view.adapter.RecordInterViewAdapter
import com.example.dudeulimproject.view.profile.ProfileActivity
import com.example.dudeulimproject.view.request_inter_view.viewmodel.RequestInterViewViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class RequestInterViewActivity :
    BaseActivity<ActivityRequestInterViewBinding>(R.layout.activity_request_inter_view) {
    private val viewModel: RequestInterViewViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.activity = this
        binding.lifecycleOwner = this

        viewModel.getRequestInterView(intent.getStringExtra("requestInterViewId").toString())
        viewModel.checkRequester(intent.getBooleanExtra("isRequester", false))

        convertBlankText(binding.textRequestInterViewDate)
        if (viewModel.interView.value!!.category != "online") {
            convertBlankText(binding.textRequestInterViewPlace)
        }
        binding.textRequestInterViewPlace.text = ""
    }

    private fun convertBlankText(textView: TextView) {
        if (textView.text.isNullOrBlank()) {
            textView.text = "미정"
        }
    }

    fun clickBackButton(view: View) {
        finish()
    }

    fun clickInfoButton(view: View) {
        val category = viewModel.interView.value!!.category
        val intent = Intent(this, InfoModifyActivity::class.java)
        intent.putExtra("category", category)
        intent.putExtra("interViewId", viewModel.interView.value!!.id)
        intent.putExtra("date", viewModel.interView.value!!.date)
        intent.putExtra("place", viewModel.interView.value!!.place)
        startActivity(intent)
    }


    fun clickChatButton(view: View) {
        val intent = Intent(this, ChatActivity::class.java)
        intent.putExtra("roomId", viewModel.interView.value!!.id)
        if (viewModel.isRequester.value!!) {
            intent.putExtra("userId", viewModel.interView.value!!.requester.id)
        } else {
            intent.putExtra("userId", viewModel.interView.value!!.publisher.id)
        }
        startActivity(intent)
    }

    fun clickProfile(view: View) {
        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra("userId", viewModel.interView.value!!.requester.id)
        startActivity(intent)
    }

    fun clickAcceptButton(view: View) {
        if (!viewModel.isRequester.value!!) {
            val function = { finish() }
            val dialog = NormalDialog(this, "인터뷰 수락", "인터뷰를 수락하시겠습니까?", function)
            dialog.show()
        }
    }
}
