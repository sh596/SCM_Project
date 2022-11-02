package com.example.dudeulimproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dudeulimproject.base.BaseActivity
import com.example.dudeulimproject.databinding.ActivitySplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
        }
    }
}