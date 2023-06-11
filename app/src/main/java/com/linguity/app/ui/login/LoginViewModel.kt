package com.linguity.app.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.linguity.app.api.responses.ResponseLogin
import com.linguity.app.data.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val repository: Repository) : ViewModel() {
    private val _toastText = MutableLiveData<String>()

    val toastText: LiveData<String> = _toastText

    fun login(email: String, password: String) {
        repository.login(email, password)
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
                    } else {
                        _toastText.value = "Failed: ${responseBody?.msg}"
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