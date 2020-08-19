package com.stenleone.fitapp.koin

import com.stenleone.fitapp.model.internet.JsonPlaceHolderFitPlan
import com.stenleone.fitapp.util.constant.ApiFitPlanConstant
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val retrofitFitPlanModule = module {
    single { provideRetrofitFitPlan() }
}

fun provideRetrofitFitPlan(): JsonPlaceHolderFitPlan {

    var interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(ApiFitPlanConstant.MAIN_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(
            RxJava2CallAdapterFactory
                .createWithScheduler(Schedulers.io()))
        .client(client)
        .build()

    val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderFitPlan::class.java)
    return jsonPlaceHolderApi
}