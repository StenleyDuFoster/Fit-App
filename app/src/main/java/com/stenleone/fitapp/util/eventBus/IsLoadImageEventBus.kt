package com.stenleone.fitapp.util.eventBus

import com.stenleone.fitapp.util.eventBus.base.BaseEventBus
import com.stenleone.fitapp.util.shared_preferences.SharedPreferencesManager
import io.reactivex.Observable

object IsLoadImageEventBus : BaseEventBus() {

    private val sharedPreferences: SharedPreferencesManager

    init {
        sharedPreferences = SharedPreferencesManager
        setObservable(sharedPreferences.getIsLoadImage())
    }

    override fun setObservable(event: Any) {
        observableEvent = Observable.just(event)
        sharedPreferences.setIsLoadImage(event as Boolean)
    }
}