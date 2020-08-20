package com.stenleone.fitapp.model.view_model

import androidx.lifecycle.MutableLiveData
import com.stenleone.fitapp.model.data.ItemDetailsFitApp
import com.stenleone.fitapp.model.view_model.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class DatailsViewModel : BaseViewModel() {

    private val liveItem = MutableLiveData<ItemDetailsFitApp>()

    fun getItem() = liveItem

    fun getItemDetails(itemId: Int, authToken: String) {

        jsonPlaceHolderFitPlan.getItem(
            itemId,
            authToken
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                {error ->
                    liveError.postValue(error.message)
                },
                {
                    response ->
                    if(response.isSuccessful) {
                        liveItem.postValue(response.body())
                    } else {
                        liveError.postValue("error code: " + response.code().toString())
                    }
                })
    }
}