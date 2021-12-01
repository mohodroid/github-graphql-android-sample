package com.mohdroid.zarinpalcodechallenge.di.module

import com.mohdroid.domain.ConfigManager
import com.mohdroid.zarinpalcodechallenge.features.common.ConfigManagerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ConfigManagerModule {

    @Singleton
    @Binds
    abstract fun bindConfig(configManagerImpl: ConfigManagerImpl): ConfigManager
}