package com.example.dudeulimproject

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import live.videosdk.rtc.android.VideoSDK

@HiltAndroidApp
class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()


    }
}