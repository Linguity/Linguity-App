package com.linguity.app.ui.spelling_quiz

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.linguity.app.R
import com.linguity.app.databinding.ActivitySpellingSubmitBinding
import com.linguity.app.ui.pronunciation_checker.PronunciationSubmitActivity
import java.util.Locale

class SpellingSubmitActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private val binding by lazy {
        ActivitySpellingSubmitBinding.inflate(layoutInflater)
    }

    private val textToSpeech by lazy {
        TextToSpeech(this@SpellingSubmitActivity, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val stringAB: String = resources.getString(R.string.spelling_quiz_page_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = stringAB
        val stringWords = intent.getStringExtra(PronunciationSubmitActivity.EXTRA_ID)
        val strWithSpace = stringWords?.replace("", " ")?.trim()
        setSpellWords(strWithSpace.toString())
        setHide()
        setAction()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale.ENGLISH)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "Language not supported", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Initialization failed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        textToSpeech.stop()
        textToSpeech.shutdown()
    }

    private fun setSpellWords(speak: String) {
        binding.apply {
            ivSSSpeak.setOnClickListener {
                val text = speak
                if (text.isNotEmpty()) {
                    textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
                }
            }
        }
    }

    private fun setAction() {
        binding.apply {
            btnSSRecord.setOnClickListener {
                setVisible()
            }
            btnSSSubmit.setOnClickListener {
                Intent(this@SpellingSubmitActivity, SpellingResultActivity::class.java).also {
                    startActivity(it)
                }
                finish()
            }
        }
    }

    private fun setHide() {
        binding.apply {
            cvSSSubmit.isVisible = false
        }
    }

    private fun setVisible() {
        binding.apply {
            cvSSSubmit.isVisible = true
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}