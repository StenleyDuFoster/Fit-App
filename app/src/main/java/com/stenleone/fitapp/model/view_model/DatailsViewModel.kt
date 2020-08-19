package com.stenleone.fitapp.model.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stenleone.fitapp.model.data.ItemDetailsFitApp
import com.stenleone.fitapp.model.internet.JsonPlaceHolderFitPlan
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class DatailsViewModel : ViewModel(), KoinComponent {

    val liveItem = MutableLiveData<ItemDetailsFitApp>()
    val liveError = MutableLiveData<Throwable>()

    fun getItem() = liveItem
    fun getError() = liveError

    fun getItemDetails(itemId: Int, authToken: String) {

        val jsonPlaceHolderFitPlan: JsonPlaceHolderFitPlan by inject()
        jsonPlaceHolderFitPlan.getItem(
            itemId,
            authToken
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                {error ->
                    liveError.postValue(error)
                },
                {
                    response ->
                    if(response.isSuccessful) {
                        liveItem.postValue(response.body())
                    }
                })
    }
}