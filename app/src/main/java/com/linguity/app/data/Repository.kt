package com.linguity.app.data

import android.content.SharedPreferences
import com.linguity.app.api.ApiService
import com.linguity.app.api.requests.LoginRequest
import com.linguity.app.api.requests.RegisterRequest
import com.linguity.app.api.responses.ResponseLogin
import com.linguity.app.api.responses.ResponseRegister
import com.linguity.app.api.responses.ResponseQuizList
import retrofit2.Call

class Repository(
    private val apiService: ApiService,
    private val sharedPreferences: SharedPreferences
) {
    fun login(loginRequest: LoginRequest): Call<ResponseLogin> {
        return apiService.login(loginRequest)
    }

    fun register(registerRequest: RegisterRequest): Call<ResponseRegister> {
        return apiService.register(registerRequest)
    }

    fun getSpellingListByLevel(level: String): Call<ResponseQuizList> {
        return apiService.getSpellingListByLevel(level)
    }

    fun getPronunciationListByLevel(level: String): Call<ResponseQuizList> {
        return apiService.getPronunciationListByLevel(level)
    }

    fun logout() {
        sharedPreferences
            .edit()
            .remove(USERNAME_KEY)
            .apply()
    }

    fun getSignedInUserName(): String? {
        return sharedPreferences.getString(USERNAME_KEY, null)
    }

    fun saveSignedInUserName(userName: String) {
        sharedPreferences
            .edit()
            .putString(USERNAME_KEY, userName)
            .apply()
    }

    companion object {
        private const val USERNAME_KEY = "signed_in_user"

        @Volatile
        private var instance: Repository? = null

        fun getInstance(
            apiService: ApiService,
            sharedPreferences: SharedPreferences
        ): Repository {
            return instance ?: synchronized(this) {
                instance = Repository(apiService, sharedPreferences)
                instance as Repository
            }
        }
    }
}