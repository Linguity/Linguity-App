package com.linguity.app.di

import android.content.Context
import com.linguity.app.api.ApiConfig
import com.linguity.app.data.Repository

object Injection {

    fun provideRepository(context: Context): Repository {
        val apiService = ApiConfig.getApiService()
        val sharedPreferences =
            context.getSharedPreferences("liguity_shared_pref", Context.MODE_PRIVATE)

        return Repository.getInstance(apiService, sharedPreferences)
    }
}