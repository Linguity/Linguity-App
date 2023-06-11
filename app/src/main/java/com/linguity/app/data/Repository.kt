package com.linguity.app.data

import com.linguity.app.api.ApiService
import com.linguity.app.api.responses.ResponseLogin
import com.linguity.app.api.responses.ResponseRegister
import retrofit2.Call

class Repository(
    private val apiService: ApiService
) {
    fun login(email: String, password: String): Call<ResponseLogin> {
        return apiService.login(email, password)
    }

    fun register(name: String, email: String, password: String): Call<ResponseRegister> {
        return apiService.register(name, email, password)
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