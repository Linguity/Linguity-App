package com.linguity.app.ui.pronunciation_checker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.linguity.app.databinding.ActivityPronunciationResultBinding

class PronunciationResultActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPronunciationResultBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}