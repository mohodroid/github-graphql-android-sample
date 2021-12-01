package com.mohdroid.zarinpalcodechallenge.features.common

import com.mohdroid.domain.ConfigManager
import com.mohdroid.zarinpalcodechallenge.BuildConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConfigManagerImpl @Inject constructor() : ConfigManager {

    override val apiHeader: String
        get() = BuildConfig.HEADER

}