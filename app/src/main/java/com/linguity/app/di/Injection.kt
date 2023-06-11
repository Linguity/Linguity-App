package com.linguity.app.di

import com.linguity.app.api.ApiConfig
import com.linguity.app.data.Repository

object Injection {

    fun provideRepository(): Repository {
        val apiService = ApiConfig.getApiService()

        return Repository.getInstance(apiService)
    }
}