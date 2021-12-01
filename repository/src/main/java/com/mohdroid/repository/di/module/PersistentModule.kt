package com.mohdroid.repository.di.module

import android.content.Context
import androidx.room.Room
import com.mohdroid.domain.DATABASE_NAME
import com.mohdroid.repository.persistent.DataBase
import com.mohdroid.repository.persistent.profile.ProfilePersistentImpl
import com.mohdroid.repository.persistent.profile.UserProfilePersistent
import com.mohdroid.repository.persistent.reposlist.UserRepositoriesPersistent
import com.mohdroid.repository.persistent.reposlist.UserRepositoriesPersistentImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [PersistentModule.BindModule::class])
class PersistentModule {

    @Singleton
    @Provides
    fun provideDataBase(ctx: Context): DataBase {
        return Room.databaseBuilder(ctx, DataBase::class.java, DATABASE_NAME).build()
    }

    @Module
    abstract class BindModule {

        @Binds
        abstract fun bindUserRepositoriesPersistent(userRepositoriesPersistentImpl: UserRepositoriesPersistentImpl): UserRepositoriesPersistent

        @Binds
        abstract fun bindUserProfilePersistent(userProfilePersistentImpl: ProfilePersistentImpl): UserProfilePersistent


    }


}