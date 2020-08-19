package com.stenleone.fitapp.model.data

import com.google.gson.annotations.SerializedName

data class LogInUserFitPlan (

    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_in")
    val expiresIn: Int,
    val jti: String,
    val scope: String,
    @SerializedName("token_type")
    val tokenType: String
)