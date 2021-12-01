package com.mohdroid.service.di

import com.mohdroid.domain.service.UserProfileService
import com.mohdroid.domain.service.UserRepositoriesService
import com.mohdroid.service.ProfileServiceImpl
import com.mohdroid.service.UserRepositoriesServiceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class  ServiceModule {

    @Binds
    abstract fun bindUserRepoService(userRepositoriesServiceImpl: UserRepositoriesServiceImpl):
            UserRepositoriesService

    @Binds
    abstract fun bindProfileService(profileServiceImpl: ProfileServiceImpl): UserProfileService

}