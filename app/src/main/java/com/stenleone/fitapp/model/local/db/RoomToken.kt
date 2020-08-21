package com.stenleone.fitapp.model.local.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.stenleone.fitapp.koin.application.App
import com.stenleone.fitapp.model.local.dao.TokenDao
import com.stenleone.fitapp.model.local.entity.TokenEntity

@Database(entities = [TokenEntity::class], version = 1, exportSchema = false)
abstract class RoomToken : RoomDatabase() {

    abstract fun TokenDao(): TokenDao

    companion object {
        @Volatile private var INSTANCE: RoomToken? = null

        fun getInstance(): RoomToken =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase().also { INSTANCE = it }
            }

        private fun buildDatabase() =
            Room.databaseBuilder(
                App.contextComponent,
                RoomToken::class.java, "token.db")
                .build()

        fun destroyInstance() {

            if (INSTANCE?.isOpen == true) {
                INSTANCE?.close()
            }

            INSTANCE = null
        }
    }
}