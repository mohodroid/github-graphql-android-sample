package com.mohdroid.domain.dto.profile.service

data class ProfileResponseService(
    val name: String,
    val avatarUrl: String?,
    val email: String,
    val followersCount: Int,
    val followingCount: Int
)