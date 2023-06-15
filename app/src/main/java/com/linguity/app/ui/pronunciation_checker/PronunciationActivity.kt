package com.linguity.app.ui.pronunciation_checker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.linguity.app.R
import com.linguity.app.databinding.ActivityPronunciationBinding

class PronunciationActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPronunciationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val stringAB: String = resources.getString(R.string.pronunciation_page_title)
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
            cvPBeginner.setOnClickListener {
                Intent(this@PronunciationActivity, PronunciationListActivity::class.java).also {
                    it.putExtra(LEVEL_EXTRA, "beginner")
                    startActivity(it)
                }
            }
            cvPIntermediate.setOnClickListener {
                Intent(this@PronunciationActivity, PronunciationListActivity::class.java).also {
                    it.putExtra(LEVEL_EXTRA, "intermediate")
                    startActivity(it)
                }
            }
            cvPAdvance.setOnClickListener {
                Intent(this@PronunciationActivity, PronunciationListActivity::class.java).also {
                    it.putExtra(LEVEL_EXTRA, "advanced")
                    startActivity(it)
                }
            }
        }
    }

    companion object {
        private const val LEVEL_EXTRA = "level"
    }
}