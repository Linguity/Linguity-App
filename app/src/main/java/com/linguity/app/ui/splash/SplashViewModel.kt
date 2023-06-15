package com.linguity.app.ui.splash

import androidx.lifecycle.ViewModel
import com.linguity.app.data.Repository

class SplashViewModel(private val repository: Repository) : ViewModel() {

    fun getSignedInUserName(): String? {
        return repository.getSignedInUserName()
    }
}