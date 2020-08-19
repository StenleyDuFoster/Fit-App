package com.stenleone.fitapp.model.view_model

import androidx.lifecycle.MutableLiveData
import com.stenleone.fitapp.model.data.LogInUserFitPlan
import com.stenleone.fitapp.model.view_model.base.BaseViewModel
import com.stenleone.fitapp.util.easyToast.makeToast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.rxkotlin.subscribeBy

class LoginViewModel : BaseViewModel() {

    private val liveUser = MutableLiveData<LogInUserFitPlan>()

    fun getUser() = liveUser

    fun logInFitPlan(userName: String, userPassword: String) {

        jsonPlaceHolderFitPlan.postAuth(
            userName,
            userPassword
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                {error ->
                    liveError.postValue(error.message)
                    makeToast(error.toString())
                },
                {
                    response ->
                    if(response.isSuccessful) {
                        liveUser.postValue(response.body())
                    } else {
                        liveError.postValue(response.code().toString())
                    }
                })
    }
}