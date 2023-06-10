package com.linguity.app.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.toSpannable
import androidx.lifecycle.ViewModelProvider
import com.linguity.app.R
import com.linguity.app.databinding.ActivityLoginBinding
import com.linguity.app.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        setComponentsOnClickListener()
    }

    private fun setComponentsOnClickListener() {

        binding.btnSignIn.setOnClickListener {

        }

        binding.tvClickableToRegister.apply {
            val textResourceUnderline = getString(R.string.register_now).toSpannable() as SpannableString
            textResourceUnderline
                .setSpan(
                    UnderlineSpan(),
                    0,
                    textResourceUnderline.length,
                    0
                ).toString()

            text = textResourceUnderline

            setOnClickListener {
                Intent(this@LoginActivity, RegisterActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }
}