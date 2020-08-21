package com.stenleone.fitapp.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "token")
data class TokenEntity (

    @PrimaryKey
    val token: String
)