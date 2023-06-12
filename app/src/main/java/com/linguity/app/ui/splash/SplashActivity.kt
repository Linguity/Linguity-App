package com.linguity.app.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.linguity.app.databinding.ActivitySplashBinding
import com.linguity.app.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val SECONDS_IN_MILISECONDS: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }, SECONDS_IN_MILISECONDS)
    }
}