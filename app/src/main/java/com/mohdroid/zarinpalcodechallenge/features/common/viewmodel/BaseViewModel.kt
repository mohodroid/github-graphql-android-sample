package com.mohdroid.zarinpalcodechallenge.features.common.viewmodel

import androidx.lifecycle.LifecycleObserver
import com.mohdroid.domain.result.Error
import com.mohdroid.zarinpalcodechallenge.features.common.Event

interface BaseViewModel : LifecycleObserver,
    ProgressViewModel,
    ErrorViewModel {
    fun handleError(error: Error) {
        hideProgressAction()
        getShowErrorEvent().postValue(Event(error.type.name))
    }
}