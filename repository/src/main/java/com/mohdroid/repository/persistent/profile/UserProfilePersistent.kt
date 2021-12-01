package com.mohdroid.repository.persistent.profile

import com.mohdroid.repository.entity.profile.ProfileEntity
import com.mohdroid.repository.persistent.DataBase
import javax.inject.Inject
import javax.inject.Singleton


interface UserProfilePersistent {

    suspend fun insertProfile(profileEntity: ProfileEntity)

    suspend fun getProfile(): ProfileEntity?

    suspend fun delete()

    suspend fun update(profileEntity: ProfileEntity): ProfileEntity
}

@Singleton
class ProfilePersistentImpl @Inject constructor(
    private val db: DataBase

) : UserProfilePersistent {

    override suspend fun insertProfile(profileEntity: ProfileEntity) {
        db.profileDao().insertProfileData(profileEntity)
    }

    override suspend fun getProfile(): ProfileEntity {
        return db.profileDao().getProfile()
    }

    override suspend fun delete() {
        db.profileDao().delete()
    }

    override suspend fun update(profileEntity: ProfileEntity): ProfileEntity {
        delete()
        insertProfile(profileEntity)
        return profileEntity
    }

}