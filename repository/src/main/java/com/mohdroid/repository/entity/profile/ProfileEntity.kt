package com.mohdroid.repository.entity.profile

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName =  "user_profile")
data class ProfileEntity(
    val name: String?,
    val avatarUrl: String?,
    val email: String,
    val followersCount: Int,
    val followingCount: Int,

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}