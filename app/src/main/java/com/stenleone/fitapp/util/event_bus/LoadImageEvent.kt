package com.stenleone.fitapp.util.event_bus

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.PublishProcessor
import org.greenrobot.eventbus.EventBus

class LoadImageEvent : EventBus() {

    private val publishProcessor = PublishProcessor.create<Any>()


    override fun postEvent(event: Any) {
        publishProcessor.onNext(event)
    }

    override fun observeEvents(): Flowable<Any> {
        return publishProcessor.serialize()
    }

    override fun observeEventsOnUi(): Flowable<Any> {
        return observeEvents()
            .observeOn(AndroidSchedulers.mainThread())
    }
}