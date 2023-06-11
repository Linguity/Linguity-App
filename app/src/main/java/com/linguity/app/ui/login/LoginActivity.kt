package com.linguity.app.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.toSpannable
import com.linguity.app.R
import com.linguity.app.databinding.ActivityLoginBinding
import com.linguity.app.helper.ViewModelFactory
import com.linguity.app.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val viewModel: LoginViewModel by viewModels {
        ViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setComponentsOnClickListener()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.toastText.observe(this) {
            showToast(it)
        }
    }

    private fun setComponentsOnClickListener() {
        binding.apply {
            btnSignIn.setOnClickListener {
                val email = edLoginEmail.text.toString()
                val password = edLoginPassword.text.toString()

                viewModel.login(email, password)
            }

            tvClickableToRegister.apply {
                val textResourceUnderline =
                    getString(R.string.register_now).toSpannable() as SpannableString
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

    private fun showToast(text: String?) {
        Toast.makeText(
            this,
            text,
            Toast.LENGTH_SHORT
        ).show()
    }
}