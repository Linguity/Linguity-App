package com.linguity.app.ui.pronunciation_checker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.linguity.app.databinding.ActivityPronunciationBinding

class PronunciationActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityPronunciationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}