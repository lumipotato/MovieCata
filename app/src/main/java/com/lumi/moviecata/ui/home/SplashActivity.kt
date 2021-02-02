package com.lumi.moviecata.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.lumi.moviecata.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(activitySplashBinding.root)

        Handler(Looper.getMainLooper()).postDelayed(
            {startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                finish()
            },2000)
    }
}