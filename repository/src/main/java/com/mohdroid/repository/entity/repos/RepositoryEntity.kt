package com.mohdroid.repository.entity.repos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repository")
data class RepositoryEntity(
    @PrimaryKey
    val cursor: String,
    val stars: Int?,
    val name: String?
)


