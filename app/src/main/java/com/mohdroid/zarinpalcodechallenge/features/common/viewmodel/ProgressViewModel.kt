package com.mohdroid.zarinpalcodechallenge.features.common.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mohdroid.zarinpalcodechallenge.features.common.Event


interface ProgressViewModel {

    fun getShowProgressEvent(): MutableLiveData<Event<Unit>>
    fun getHideProgressEvent(): MutableLiveData<Event<Unit>>

    fun showProgressAction() {
        getShowProgressEvent().postValue(Event(Unit))
    }

    fun hideProgressAction() {
        getHideProgressEvent().postValue(Event(Unit))
    }
}
