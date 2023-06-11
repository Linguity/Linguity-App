package com.linguity.app.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.linguity.app.api.requests.RegisterRequest
import com.linguity.app.api.responses.ResponseRegister
import com.linguity.app.data.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(private val repository: Repository) : ViewModel() {
    private val _toastText = MutableLiveData<String>()
    private val _isSucceed = MutableLiveData<Boolean>()

    val toastText: LiveData<String> = _toastText
    val isSucceed: LiveData<Boolean> = _isSucceed

    fun register(name: String, email: String, password: String) {
        val request = RegisterRequest(name, email, password)
        repository.register(request)
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
                        _isSucceed.value = true
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