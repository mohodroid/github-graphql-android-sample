package com.mohdroid.zarinpalcodechallenge.features.common.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mohdroid.zarinpalcodechallenge.features.common.Event


interface ErrorViewModel {
    fun getShowErrorEvent(): MutableLiveData<Event<String>>
}
