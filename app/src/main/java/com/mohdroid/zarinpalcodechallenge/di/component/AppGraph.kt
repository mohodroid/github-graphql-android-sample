package com.mohdroid.zarinpalcodechallenge.di.component

import android.content.Context
import com.mohdroid.repository.di.module.NetworkModule
import com.mohdroid.repository.di.module.PersistentModule
import com.mohdroid.repository.di.module.RepoModule
import com.mohdroid.service.di.ServiceModule
import com.mohdroid.zarinpalcodechallenge.MainActivity
import com.mohdroid.zarinpalcodechallenge.di.module.ConfigManagerModule
import com.mohdroid.zarinpalcodechallenge.di.module.ImageHelperModule
import com.mohdroid.zarinpalcodechallenge.di.module.ViewModelModule
import com.mohdroid.zarinpalcodechallenge.features.profile.UserProfileFragment
import com.mohdroid.zarinpalcodechallenge.features.repositorieslist.UserRepositoriesListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ServiceModule::class,
        NetworkModule::class,
        PersistentModule::class,
        RepoModule::class,
        ViewModelModule::class,
        ImageHelperModule::class,
        ConfigManagerModule::class
    ]
)
interface AppGraph {

    fun inject(frg: UserProfileFragment)

    fun inject(mainActivity: MainActivity)

    fun inject(frg: UserRepositoriesListFragment)

    /**
     * Factory to create instance of the AppGraph
     */
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppGraph
    }


}