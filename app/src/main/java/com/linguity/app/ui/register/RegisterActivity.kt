package com.linguity.app.ui.register

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.toSpannable
import androidx.lifecycle.ViewModelProvider
import com.linguity.app.R
import com.linguity.app.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        setComponentsOnClickListener()

    }

    private fun setComponentsOnClickListener() {
        binding.btnSignUp.setOnClickListener {

        }

        binding.tvClickableToLogin.apply {
            val textResourceUnderline = getString(R.string.login_here).toSpannable() as SpannableString
            textResourceUnderline
                .setSpan(
                    UnderlineSpan(),
                    0,
                    textResourceUnderline.length,
                    0
                )

            text = textResourceUnderline

            setOnClickListener {
                this@RegisterActivity.finish()
            }
        }
    }
}