package com.stenleone.fitapp.util.interceptor

import com.stenleone.fitapp.util.shared_preferences.SharedPreferencesManager
import okhttp3.Interceptor
import okhttp3.Response

class BasicHeaderInterceptor : Interceptor {

    private val accessToken: String?

    init {
        accessToken = SharedPreferencesManager.getToken()
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json")

            if (accessToken == null) {
                chain.request()
            } else {
                builder.addHeader("Authorization", "Bearer ${accessToken}")
            }

        return chain.proceed(builder.build())
    }
}