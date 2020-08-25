package com.stenleone.fitapp.util.eventBus

import com.stenleone.fitapp.util.shared_preferences.SharedPreferencesManager
import io.reactivex.Observable

object LoadImageEventBus {

    val sharedPreferences = SharedPreferencesManager()
    var isLoadImageObservable = Observable.just(sharedPreferences.getIsLoadImage())

    fun setIsLoad(isLoad: Boolean) {
        isLoadImageObservable = Observable.just(isLoad)
        sharedPreferences.setIsLoadImage(isLoad)
    }

    fun getObservableIsLoad() = isLoadImageObservable
}