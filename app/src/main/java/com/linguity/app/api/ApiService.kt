package com.linguity.app.api

import com.linguity.app.api.requests.LoginRequest
import com.linguity.app.api.requests.RegisterRequest
import com.linguity.app.api.responses.ResponseLogin
import com.linguity.app.api.responses.ResponseRegister
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    fun login(
        @Body loginRequest: LoginRequest
    ): Call<ResponseLogin>

    @POST("register")
    fun register(
        @Body registerRequest: RegisterRequest
    ): Call<ResponseRegister>

}