package com.mohdroid.zarinpalcodechallenge.di.module

import com.mohdroid.zarinpalcodechallenge.utils.LoadImageHelper
import com.mohdroid.zarinpalcodechallenge.utils.LoadImageHelperImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ImageHelperModule {

    @Binds
    abstract fun provideLoadingImageHelper(loadImageLoaderImpl: LoadImageHelperImpl): LoadImageHelper


}