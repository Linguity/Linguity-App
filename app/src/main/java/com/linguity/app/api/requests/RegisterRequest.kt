package com.linguity.app.api.requests

data class RegisterRequest(
    val userName: String,
    val email: String,
    val password: String,
)
