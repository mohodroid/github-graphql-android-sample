package com.mohdroid.repository.di.module

import com.mohdroid.domain.repository.UserProfileRepo
import com.mohdroid.domain.repository.UserRepositoriesRepo
import com.mohdroid.repository.features.ProfileRepoImpl
import com.mohdroid.repository.features.UserRepositoriesRepoImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepoModule {

    @Binds
    abstract fun bindUserRepositoriesRepo(userRepositoriesRepoImpl: UserRepositoriesRepoImpl): UserRepositoriesRepo

    @Binds
    abstract fun bindProfileRepo(profileRepoImpl: ProfileRepoImpl): UserProfileRepo

}