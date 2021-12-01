package com.mohdroid.domain.common

import com.mohdroid.domain.dto.profile.ProfileResponse
import com.mohdroid.domain.dto.profile.service.ProfileResponseService
import com.mohdroid.domain.dto.repositories.Node
import com.mohdroid.domain.dto.repositories.service.RepoInfo


fun ProfileResponse.toProfileResponseService(): ProfileResponseService {
    return ProfileResponseService(
        this.name ?: "UNKNOWN",
        this.avatarUrl,
        this.email,
        this.followersCount,
        this.followingCount
    )
}

fun Node.toRepoInfo(): RepoInfo {
   return RepoInfo(this.name ?: "UNKNOWN", this.stargazerCount ?: 0)
}