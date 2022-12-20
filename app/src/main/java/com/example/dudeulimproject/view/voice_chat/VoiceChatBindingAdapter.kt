package com.example.dudeulimproject.view.voice_chat

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.dudeulimproject.R

object VoiceChatBindingAdapter {
    @BindingAdapter("bindConnection")
    @JvmStatic
    fun bindConnection(view: TextView, isConnect: Boolean){
        if(isConnect){
            view.text = "음성 채팅 종료"
        }else {
            view.text = "음성 채팅 연결"
        }
    }
    @BindingAdapter("bindMic")
    @JvmStatic
    fun bindMic(view: ImageView, MicEnabled: Boolean){
        if(MicEnabled){
            view.setImageResource(R.drawable.mic)
        }else {
            view.setImageResource(R.drawable.mic_unabble)
        }
    }

    @BindingAdapter("bindCam")
    @JvmStatic
    fun bindCam(view: ImageView, CamEnabled: Boolean){
        if(CamEnabled){
            view.setImageResource(R.drawable.camera)
        }else {
            view.setImageResource(R.drawable.camera_unable)
        }
    }
}