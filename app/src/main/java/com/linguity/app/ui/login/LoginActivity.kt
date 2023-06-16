package com.linguity.app.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.text.toSpannable
import androidx.core.util.Pair
import androidx.core.view.isVisible
import com.linguity.app.R
import com.linguity.app.databinding.ActivityLoginBinding
import com.linguity.app.helper.ViewModelFactory
import com.linguity.app.ui.main.MainActivity
import com.linguity.app.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val viewModel: LoginViewModel by viewModels {
        ViewModelFactory(this)
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

        viewModel.isSucceed.observe(this) { isSucceed ->
            if (isSucceed) {
                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                }

                this.finish()
            } else {
                binding.apply {
                    edLoginEmail.text?.clear()
                    edLoginPassword.text?.clear()
                }
            }
        }

        viewModel.isLoading.observe(this) {
            binding.cvLoading.isVisible = it
        }
    }

    private fun setComponentsOnClickListener() {
        binding.apply {
            btnSignIn.setOnClickListener {

                if (!edLoginEmail.error.isNullOrEmpty() && !edLoginPassword.error.isNullOrEmpty()) {
                    showToast(getString(R.string.empty_field_message))
                } else {
                    val email = edLoginEmail.text.toString()
                    val password = edLoginPassword.text.toString()

                    viewModel.login(email, password)
                }
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
                    val intent = Intent(this@LoginActivity, RegisterActivity::class.java)

                    val optionsCompat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(
                            this@LoginActivity,
                            Pair(binding.edLoginEmail, "email"),
                            Pair(binding.edLoginPassword, "password")
                        ).toBundle()

                    startActivity(intent, optionsCompat)
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