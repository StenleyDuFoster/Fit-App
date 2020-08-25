package com.stenleone.fitapp.model.view_model

import androidx.lifecycle.MutableLiveData
import com.stenleone.fitapp.model.data.details.ItemListDetailsFitApp
import com.stenleone.fitapp.model.view_model.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class DetailsViewModel : BaseViewModel() {

    private val liveItem = MutableLiveData<ItemListDetailsFitApp>()

    fun getItem() = liveItem

    fun getItemDetails(itemId: Int) {

        jsonPlaceHolderFitPlan.getItem(
            itemId
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