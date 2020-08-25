package com.stenleone.fitapp.util.eventBus.base

import io.reactivex.Observable

abstract class BaseEventBus {

    protected lateinit var observableEvent: Observable<Any>

    open fun setObservable(event: Any) {
        observableEvent = Observable.just(event)
    }

    open fun getObservable() = observableEvent
}