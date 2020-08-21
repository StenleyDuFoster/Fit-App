package com.stenleone.fitapp.model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.stenleone.fitapp.model.local.entity.TokenEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
open interface TokenDao {

    @Insert
    fun saveToken(accessToken: TokenEntity): Completable

    @Query("SELECT * FROM token")
    fun getToken(): Flowable<List<TokenEntity?>?>
}