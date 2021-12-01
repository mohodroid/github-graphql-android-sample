package com.mohdroid.zarinpalcodechallenge.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohdroid.zarinpalcodechallenge.MainViewModel
import com.mohdroid.zarinpalcodechallenge.di.qualifier.ViewModelKey
import com.mohdroid.zarinpalcodechallenge.features.common.viewmodel.ViewModelFactory
import com.mohdroid.zarinpalcodechallenge.features.profile.UserProfileViewModel
import com.mohdroid.zarinpalcodechallenge.features.repositorieslist.RepositoriesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepositoriesListViewModel::class)
    abstract fun bindRepoListViewModel(repositoriesListViewModel: RepositoriesListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel::class)
    abstract fun bindProfileViewModel(userProfileViewModel: UserProfileViewModel): ViewModel

}