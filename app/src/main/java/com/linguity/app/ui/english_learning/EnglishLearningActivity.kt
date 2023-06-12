package com.linguity.app.ui.english_learning

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.linguity.app.R

class EnglishLearningActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_english_learning)

        var stringAB: String = resources.getString(R.string.english_learning_page_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = stringAB
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}