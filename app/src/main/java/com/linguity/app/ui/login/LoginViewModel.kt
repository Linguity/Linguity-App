package com.linguity.app.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.linguity.app.api.requests.LoginRequest
import com.linguity.app.api.responses.ResponseLogin
import com.linguity.app.data.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val repository: Repository) : ViewModel() {
    private val _toastText = MutableLiveData<String>()
    private val _isSucceed = MutableLiveData<Boolean>()

    val toastText: LiveData<String> = _toastText
    val isSucceed: LiveData<Boolean> = _isSucceed

    fun login(email: String, password: String) {
        val request = LoginRequest(email, password)
        repository.login(request)
            .enqueue(object : Callback<ResponseLogin> {
                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    val responseBody = response.body()
                    if (response.isSuccessful) {
                        responseBody?.msg.let {
                            _toastText.value = it
                        }
                        _isSucceed.value = true
                    } else {
                        _toastText.value = "Failed: Invalid email or password"
                    }

                    /*
                        TODO: responseBody.tokenId
                     */
                }

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    _toastText.value = "Failed: ${t.message}"
                }

            })
    }
}