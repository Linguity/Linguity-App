package com.linguity.app.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.linguity.app.data.Repository

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val _toastText = MutableLiveData<String>()

    val toastText: LiveData<String> = _toastText

    fun logout() {
        repository.logout()
        _toastText.value = "Logged Out"
    }
}