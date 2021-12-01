package com.mohdroid.zarinpalcodechallenge.di.module

import androidx.lifecycle.ViewModelProvider
import com.mohdroid.zarinpalcodechallenge.MainViewModel
import com.mohdroid.zarinpalcodechallenge.di.qualifier.ViewModelKey
import com.mohdroid.zarinpalcodechallenge.features.common.viewmodel.AbsViewModel
import com.mohdroid.zarinpalcodechallenge.features.common.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(mainViewModel: MainViewModel): AbsViewModel


}