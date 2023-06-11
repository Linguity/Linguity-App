package com.linguity.app.ui.register

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.toSpannable
import com.linguity.app.R
import com.linguity.app.databinding.ActivityRegisterBinding
import com.linguity.app.helper.ViewModelFactory

class RegisterActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }
    private val viewModel: RegisterViewModel by viewModels {
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
            btnSignUp.setOnClickListener {
                val name = edRegisterName.text.toString()
                val email = edRegisterEmail.text.toString()
                val password = edRegisterPassword.text.toString()

                viewModel.register(name, email, password)
            }

            tvClickableToLogin.apply {
                val textResourceUnderline =
                    getString(R.string.login_here).toSpannable() as SpannableString
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

    private fun showToast(text: String?) {
        Toast.makeText(
            this,
            text,
            Toast.LENGTH_SHORT
        ).show()
    }
}