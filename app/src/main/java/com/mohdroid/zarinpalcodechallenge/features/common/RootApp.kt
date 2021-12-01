package com.mohdroid.zarinpalcodechallenge.features.common

import android.app.Application
import com.mohdroid.zarinpalcodechallenge.di.component.AppGraph
import com.mohdroid.zarinpalcodechallenge.di.component.DaggerAppGraph

class RootApp : Application() {

    lateinit var appGraph: AppGraph
    override fun onCreate() {
        super.onCreate()
        appGraph = DaggerAppGraph.factory().create(this)
    }

}