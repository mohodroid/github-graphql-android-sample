package com.mohdroid.zarinpalcodechallenge.features.common.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohdroid.zarinpalcodechallenge.features.common.Event

abstract class AbsViewModel : ViewModel(),
    BaseViewModel {
    /**
     * General
     */
    private val showProgressEvent: MutableLiveData<Event<Unit>> = MutableLiveData()
    private val hideProgressEvent: MutableLiveData<Event<Unit>> = MutableLiveData()
    private val showErrorEvent: MutableLiveData<Event<String>> = MutableLiveData()

    override fun getHideProgressEvent(): MutableLiveData<Event<Unit>> = hideProgressEvent

    override fun getShowProgressEvent(): MutableLiveData<Event<Unit>> = showProgressEvent

    override fun getShowErrorEvent(): MutableLiveData<Event<String>> = showErrorEvent

}