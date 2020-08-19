package com.stenleone.fitapp.model.view_model.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stenleone.fitapp.model.internet.JsonPlaceHolderFitPlan
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

abstract class BaseViewModel : ViewModel(), KoinComponent {

    protected val liveError = MutableLiveData<String>()

    protected val jsonPlaceHolderFitPlan: JsonPlaceHolderFitPlan by inject()

    fun getError() = liveError
}