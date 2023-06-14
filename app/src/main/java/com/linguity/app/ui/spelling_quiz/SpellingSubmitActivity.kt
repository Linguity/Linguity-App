package com.linguity.app.ui.spelling_quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.linguity.app.R
import com.linguity.app.databinding.ActivitySpellingSubmitBinding

class SpellingSubmitActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySpellingSubmitBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val stringAB: String = resources.getString(R.string.spelling_quiz_page_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = stringAB
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}