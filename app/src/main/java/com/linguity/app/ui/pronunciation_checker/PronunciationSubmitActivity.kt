package com.linguity.app.ui.pronunciation_checker

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import android.Manifest
import android.content.ContextWrapper
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import android.os.Environment
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import com.linguity.app.R
import com.linguity.app.api.responses.Quiz
import com.linguity.app.databinding.ActivityPronunciationSubmitBinding
import com.linguity.app.helper.ViewModelFactory
import com.linguity.app.ui.pronunciation_checker.view_model.PronunciationSubmitViewModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PronunciationSubmitActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private val binding by lazy {
        ActivityPronunciationSubmitBinding.inflate(layoutInflater)
    }
    private val viewModel: PronunciationSubmitViewModel by viewModels {
        ViewModelFactory(this)
    }
    private val textToSpeech by lazy {
        TextToSpeech(this@PronunciationSubmitActivity, this)
    }

    private var mediaRecorder: MediaRecorder? = null
    private lateinit var mediaPlayer: MediaPlayer

    private var quiz: Quiz? = null
    private var fileAudio: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val stringAB: String = resources.getString(R.string.pronunciation_page_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = stringAB
        val stringWords = intent.getStringExtra(EXTRA_ID)

        quiz = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_QUIZ, Quiz::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_QUIZ)
        }

        setSpeakWords(stringWords.toString())
        setHide()
        setAction()
        observeViewModel()

        if (isMicrophonePresent()) {
            getMicrophonePermission()
        }

    }

    private fun observeViewModel() {
        viewModel.isSuccess.observe(this) {
            if (it) {
                viewModel.status.observe(this) { status ->
                    Intent(
                        this@PronunciationSubmitActivity,
                        PronunciationResultActivity::class.java
                    ).also { intent ->
                        intent.putExtra("status", status)
                        startActivity(intent)
                    }
                    finish()
                }
            }

        }
    }

    private fun getMicrophonePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), MICROPHONE_PERMISSION_CODE)
        }
    }


    private fun isMicrophonePresent(): Boolean {
        return packageManager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE)
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

    private fun setSpeakWords(speak: String) {
        binding.apply {
            ivPSSpeak.setOnClickListener {
                val text = speak
                if (text.isNotEmpty()) {
                    textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
                }
            }
        }
    }

    private fun setAction() {
        binding.apply {
            btnPSRecord.setOnClickListener {
                mediaRecorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    MediaRecorder(this@PronunciationSubmitActivity)
                } else {
                    MediaRecorder()
                }

                mediaRecorder?.let {
                    it.apply {
                        setAudioSource(MediaRecorder.AudioSource.MIC)
                        setOutputFormat(MediaRecorder.OutputFormat.DEFAULT)
                        setOutputFile(getRecordingPath())
                        setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                    }

                    try {
                        it.prepare()
                        it.start()

                        Toast.makeText(this@PronunciationSubmitActivity, "Recording started...", Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                btnPlay.isEnabled = false
                btnStopRecord.isEnabled = true
            }

            btnStopRecord.setOnClickListener {
                mediaRecorder?.stop()
                mediaRecorder?.release()
                mediaRecorder = null

                btnPlay.isEnabled = true
                btnPSRecord.isEnabled = false
                setVisible()

                Toast.makeText(this@PronunciationSubmitActivity, "Recording stopped...", Toast.LENGTH_SHORT).show()
            }

            btnPlay.setOnClickListener {
                try {
                    mediaPlayer = MediaPlayer()
                    mediaPlayer.setDataSource(fileAudio?.absolutePath)
                    mediaPlayer.prepare()
                    mediaPlayer.start()

                    Toast.makeText(this@PronunciationSubmitActivity, "Recording playing...", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            btnPSSubmit.setOnClickListener {
                if (quiz != null && fileAudio != null) {
                    quiz?.id?.let { id ->
                        fileAudio?.let { file ->
                            viewModel.postAnswer(id, file)
                        }
                    }
                }
            }
        }
    }

    private fun getRecordingPath(): String {
        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd_hh.mm.ss", Locale.getDefault())
        val dateFormat = simpleDateFormat.format(Date())
        val musicDirectory = ContextWrapper(applicationContext).getExternalFilesDir(Environment.DIRECTORY_MUSIC)
        val file = File(musicDirectory, "linguity$dateFormat.wav")
        fileAudio = file
        return file.absolutePath
    }

    private fun setHide() {
        binding.apply {
            cvPSSubmit.isVisible = false
        }
    }

    private fun setVisible() {
        binding.apply {
            cvPSSubmit.isVisible = true
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        private const val EXTRA_QUIZ = "quiz"
        private const val MICROPHONE_PERMISSION_CODE = 200
    }
}