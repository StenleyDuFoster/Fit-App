package com.stenleone.fitapp.util.shared_preferences

import android.content.SharedPreferences
import com.stenleone.fitapp.koin.application.App
import com.stenleone.fitapp.util.constant.SharedManagerConstant

class SharedPreferencesManager {

    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = App.contextComponent.getSharedPreferences(SharedManagerConstant.TOKEN, 0)
    }

    fun setToken(token: String?) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(SharedManagerConstant.TOKEN, token)
        editor.apply()
    }

    fun getToken(): String? = sharedPreferences.getString(SharedManagerConstant.TOKEN, null)
}