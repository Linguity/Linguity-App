package com.linguity.app.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.linguity.app.databinding.ActivityMainBinding
import com.linguity.app.ui.english_learning.EnglishLearningActivity
import com.linguity.app.ui.pronunciation_checker.PronunciationActivity
import com.linguity.app.ui.spelling_quiz.SpellingActivity

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.btnLogout.setOnClickListener {
            viewModel.logout()
        }

        setNavigation()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.toastText.observe(this) {
            Toast.makeText(
                this,
                it,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setNavigation() {
        binding.apply {

            // English Learning
            cvEnglishLearning.setOnClickListener {
                Intent(this@MainActivity, EnglishLearningActivity::class.java).also {
                    startActivity(it)
                }
            }

            // Pronunciation Checker
            cvPronunciation.setOnClickListener {
                Intent(this@MainActivity, PronunciationActivity::class.java).also {
                    startActivity(it)
                }
            }

            // Spelling Quiz
            cvSpelling.setOnClickListener {
                Intent(this@MainActivity, SpellingActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }
}