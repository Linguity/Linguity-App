package com.linguity.app.helper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.linguity.app.di.Injection
import com.linguity.app.ui.login.LoginViewModel
import com.linguity.app.ui.register.RegisterViewModel

class ViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(Injection.provideRepository()) as T
        } else if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(Injection.provideRepository()) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
    }
}