package com.stenleone.fitapp.model.data.details

data class DetailsWorkOut(

    val completed: Boolean,
    val defaultRestPeriod: Int,
    val description: String,
    val equipment: String,
    val expectedDuration: Int,
    val id: Int,
    val imageTv: String,
    val imageUrl: String,
    val mobileOnly: Boolean,
    val name: String,
    val offset: Int,
    val vCutOffset: Int
)