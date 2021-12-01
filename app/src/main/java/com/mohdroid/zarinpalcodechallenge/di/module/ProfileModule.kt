package com.mohdroid.zarinpalcodechallenge.di.module

import com.mohdroid.zarinpalcodechallenge.di.qualifier.ViewModelKey
import com.mohdroid.zarinpalcodechallenge.features.common.viewmodel.AbsViewModel
import com.mohdroid.zarinpalcodechallenge.features.profile.UserProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProfileModule {

//    @Binds
//    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel::class)
    abstract fun bindViewModel(userProfileViewModel: UserProfileViewModel): AbsViewModel

}