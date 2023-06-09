package com.linguity.app.ui.spelling_quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.linguity.app.databinding.ActivitySpellingBinding

class SpellingActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySpellingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}