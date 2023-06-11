package com.linguity.app.data

import com.linguity.app.api.ApiService
import com.linguity.app.api.requests.LoginRequest
import com.linguity.app.api.requests.RegisterRequest
import com.linguity.app.api.responses.ResponseLogin
import com.linguity.app.api.responses.ResponseRegister
import retrofit2.Call

class Repository(
    private val apiService: ApiService
) {
    fun login(loginRequest: LoginRequest): Call<ResponseLogin> {
        return apiService.login(loginRequest)
    }

    fun register(registerRequest: RegisterRequest): Call<ResponseRegister> {
        return apiService.register(registerRequest)
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(apiService: ApiService): Repository {
            return instance ?: synchronized(this) {
                instance = Repository(apiService)
                instance as Repository
            }
        }
    }
}