package com.stenleone.fitapp.model.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stenleone.fitapp.model.data.LogInUserFitPlan
import com.stenleone.fitapp.model.internet.JsonPlaceHolderFitPlan
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.rxkotlin.subscribeBy
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class LoginViewModel : ViewModel(), KoinComponent{

    val liveUser = MutableLiveData<LogInUserFitPlan>()
    val liveError = MutableLiveData<Throwable>()

    fun getUser() = liveUser
    fun getError() = liveError

    fun logInFitPlan(userName: String, userPassword: String) {

        val jsonPlaceHolderFitPlan: JsonPlaceHolderFitPlan by inject()
        jsonPlaceHolderFitPlan.postAuth(
            userName,
            userPassword
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
                        liveUser.postValue(response.body())
                    }
                })
    }
}