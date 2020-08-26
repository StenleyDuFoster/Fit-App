package com.stenleone.fitapp.util.shared_preferences

import android.content.SharedPreferences

import com.stenleone.fitapp.koin.application.App
import com.stenleone.fitapp.util.constant.SharedManagerConstant

object SharedPreferencesManager {

    private val sharedPreferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        sharedPreferences =
            App.contextComponent.getSharedPreferences(SharedManagerConstant.TOKEN, 0)
        editor = sharedPreferences.edit()
    }

    fun setToken(token: String?) {
        editor.putString(SharedManagerConstant.TOKEN, token)
        editor.apply()
    }

    fun setIsLoadImage(isLoad: Boolean) {
        editor.putBoolean(SharedManagerConstant.LOAD_IMAGE, isLoad)
        editor.apply()
    }

    fun getToken(): String? = sharedPreferences.getString(SharedManagerConstant.TOKEN, null)
    fun getIsLoadImage(): Boolean =
        sharedPreferences.getBoolean(SharedManagerConstant.LOAD_IMAGE, true)
}