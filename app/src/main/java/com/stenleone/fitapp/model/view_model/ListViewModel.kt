package com.stenleone.fitapp.model.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stenleone.fitapp.model.data.ItemListFitPlan
import com.stenleone.fitapp.model.internet.JsonPlaceHolderFitPlan
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class ListViewModel : ViewModel(), KoinComponent {

    val liveListItem = MutableLiveData<ItemListFitPlan>()
    val liveError = MutableLiveData<Throwable>()

    fun getList() = liveListItem
    fun getError() = liveError

    fun getListFitPlan(authToken: String) {

        val jsonPlaceHolderFitPlan: JsonPlaceHolderFitPlan by inject()
        jsonPlaceHolderFitPlan.getListItem(
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
                        liveListItem.postValue(response.body())
                    }
                })
    }
}