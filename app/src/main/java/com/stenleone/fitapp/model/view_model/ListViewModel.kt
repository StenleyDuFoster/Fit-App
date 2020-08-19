package com.stenleone.fitapp.model.view_model

import androidx.lifecycle.MutableLiveData
import com.stenleone.fitapp.model.data.ItemListFitPlan
import com.stenleone.fitapp.model.view_model.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ListViewModel : BaseViewModel() {

    private val liveListItem = MutableLiveData<ItemListFitPlan>()

    fun getList() = liveListItem

    fun getListFitPlan(authToken: String) {

        jsonPlaceHolderFitPlan.getListItem(
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
                        liveListItem.postValue(response.body())
                    } else {
                        liveError.postValue(response.code().toString())
                    }
                })
    }
}