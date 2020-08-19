package com.stenleone.fitapp.model.internet

import com.stenleone.fitapp.model.data.ItemDetailsFitApp
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
    fun getListItem(@Query("Authorization") auth: String): Single<Response<ItemListFitPlan>>

    @GET(ApiFitPlanConstant.ITEM_DETAILS)
    fun getItem(
        @Query("planId") id: Int,
        @Query("Authorization") auth: String
    ): Single<Response<ItemDetailsFitApp>>
}