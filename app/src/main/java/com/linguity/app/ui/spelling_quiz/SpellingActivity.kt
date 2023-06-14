package com.linguity.app.ui.spelling_quiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.linguity.app.R
import com.linguity.app.databinding.ActivitySpellingBinding

class SpellingActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySpellingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val stringAB: String = resources.getString(R.string.spelling_quiz_page_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = stringAB

        setNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun setNavigation() {
        binding.apply {
            cvSBeginner.setOnClickListener {
                Intent(this@SpellingActivity, SpellingListActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }
}