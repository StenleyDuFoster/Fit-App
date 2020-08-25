package com.stenleone.fitapp.model.view_model

import androidx.lifecycle.MutableLiveData
import com.stenleone.fitapp.model.data.LogInUserFitPlan
import com.stenleone.fitapp.model.view_model.base.BaseViewModel
import com.stenleone.fitapp.util.easyToast.makeToast
import com.stenleone.fitapp.util.shared_preferences.SharedPreferencesManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.rxkotlin.subscribeBy

class LoginViewModel : BaseViewModel() {

    private val liveUser = MutableLiveData<LogInUserFitPlan>()
    private val sharedPreferences = SharedPreferencesManager()

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

                        sharedPreferences.setToken(response.body()!!.accessToken)
                        liveUser.postValue(response.body())
                    } else {
                        liveError.postValue("error code: " + response.code().toString())
                    }
                })
    }
}