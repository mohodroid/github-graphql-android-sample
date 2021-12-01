package com.mohdroid.repository.persistent.profile

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mohdroid.repository.entity.profile.ProfileEntity

@Dao
interface UserProfileDao {

    @Insert
    suspend fun insertProfileData(profileEntity: ProfileEntity)

    @Query("SELECT * FROM user_profile")
    suspend fun getProfile(): ProfileEntity

    @Query("DELETE FROM user_profile")
    suspend fun delete()

}