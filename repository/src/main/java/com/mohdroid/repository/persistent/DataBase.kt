package com.mohdroid.repository.persistent

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mohdroid.repository.entity.profile.ProfileEntity
import com.mohdroid.repository.entity.repos.RepositoryEntity
import com.mohdroid.repository.persistent.profile.UserProfileDao
import com.mohdroid.repository.persistent.reposlist.UserReposDao

@Database(
    entities = [
        RepositoryEntity::class,
        ProfileEntity::class],
    exportSchema = true,
    version = 1
)
abstract class DataBase : RoomDatabase() {

    abstract fun reposDao(): UserReposDao

    abstract fun profileDao(): UserProfileDao

}