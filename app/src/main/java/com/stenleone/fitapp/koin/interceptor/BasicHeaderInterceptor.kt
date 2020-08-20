package com.stenleone.fitapp.koin.interceptor

import com.stenleone.fitapp.util.Token
import okhttp3.Interceptor
import okhttp3.Response

class BasicHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json")

        val accessToken = Token.token

        if (accessToken.isNullOrEmpty()) {
            chain.request()
        } else {
            builder.addHeader("Authorization", "Bearer $accessToken")
        }
        return chain.proceed(builder.build())
    }
}