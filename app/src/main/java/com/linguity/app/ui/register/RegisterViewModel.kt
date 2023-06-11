package com.linguity.app.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.linguity.app.api.responses.ResponseRegister
import com.linguity.app.data.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(private val repository: Repository) : ViewModel() {
    private val _toastText = MutableLiveData<String>()

    val toastText: LiveData<String> = _toastText

    fun register(name: String, email: String, password: String) {
        repository.register(name, email, password)
            .enqueue(object : Callback<ResponseRegister> {
                override fun onResponse(
                    call: Call<ResponseRegister>,
                    response: Response<ResponseRegister>
                ) {
                    val responseBody = response.body()
                    if (response.isSuccessful) {
                        responseBody?.msg.let {
                            _toastText.value = it
                        }
                    } else {
                        _toastText.value = "Failed: ${responseBody?.msg}"
                    }

                }

                override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                    _toastText.value = "Failed: ${t.message}"
                }
            })
    }
}