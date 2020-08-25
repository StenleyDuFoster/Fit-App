package com.stenleone.fitapp.model.network

import com.stenleone.fitapp.model.data.details.ItemListDetailsFitApp
import com.stenleone.fitapp.model.data.ItemListFitPlan
import com.stenleone.fitapp.model.data.LogInUserFitPlan
import com.stenleone.fitapp.util.constant.ApiFitPlanConstant

import io.reactivex.Single

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.Response

interface JsonPlaceHolderFitPlan {

    @POST(ApiFitPlanConstant.LOGIN)
    fun postAuth(
        @Query("username") username: String,
        @Query("password") password: String
    ): Single<Response<LogInUserFitPlan>>

    @GET(ApiFitPlanConstant.CONTENT_LIST)
    fun getListItem(): Single<Response<ItemListFitPlan>>

    @GET(ApiFitPlanConstant.ITEM_DETAILS)
    fun getItem(
        @Query("planId") id: Int
    ): Single<Response<ItemListDetailsFitApp>>
}