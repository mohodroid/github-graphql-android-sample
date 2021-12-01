package com.mohdroid.domain.dto.profile


data class ProfileResponse(
    val name: String?,
    val avatarUrl: String?,
    val email: String,
    val followersCount: Int,
    val followingCount: Int
)