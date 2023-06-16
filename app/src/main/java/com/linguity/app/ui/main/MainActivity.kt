package com.linguity.app.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.linguity.app.databinding.ActivityMainBinding
import com.linguity.app.helper.ViewModelFactory
import com.linguity.app.ui.english_learning.EnglishLearningActivity
import com.linguity.app.ui.login.LoginActivity
import com.linguity.app.ui.pronunciation_checker.PronunciationActivity
import com.linguity.app.ui.spelling_quiz.SpellingActivity

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: MainViewModel by viewModels {
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setComponentsOnClickListener()
        setNavigation()
        observeViewModel()
    }

    private fun setComponentsOnClickListener() {
        binding.btnLogout.setOnClickListener {
            viewModel.logout()

            Intent(this, LoginActivity::class.java).also {
                startActivity(it)
            }

            finish()
        }
    }

    private fun observeViewModel() {
        viewModel.toastText.observe(this) {
            Toast.makeText(
                this,
                it,
                Toast.LENGTH_SHORT
            ).show()
        }

        viewModel.username.observe(this) {
            binding.tvUsername.text = it
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