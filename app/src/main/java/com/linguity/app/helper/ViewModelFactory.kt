package com.linguity.app.helper

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.linguity.app.di.Injection
import com.linguity.app.ui.login.LoginViewModel
import com.linguity.app.ui.main.MainViewModel
import com.linguity.app.ui.pronunciation_checker.view_model.PronunciationListViewModel
import com.linguity.app.ui.register.RegisterViewModel
import com.linguity.app.ui.spelling_quiz.view_model.SpellingListViewModel
import com.linguity.app.ui.splash.SplashViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(Injection.provideRepository(context)) as T
        } else if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(Injection.provideRepository(context)) as T
        } else if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(Injection.provideRepository(context)) as T
        } else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(Injection.provideRepository(context)) as T
        } else if (modelClass.isAssignableFrom(SpellingListViewModel::class.java)) {
            return SpellingListViewModel(Injection.provideRepository(context)) as T
        } else if (modelClass.isAssignableFrom(PronunciationListViewModel::class.java)) {
            return PronunciationListViewModel(Injection.provideRepository(context)) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
    }
}