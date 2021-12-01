package com.mohdroid.repository.common

import com.mohdroid.domain.dto.profile.ProfileResponse
import com.mohdroid.domain.dto.repositories.Edge
import com.mohdroid.domain.dto.repositories.Node
import com.mohdroid.repository.entity.profile.ProfileEntity
import com.mohdroid.repository.entity.repos.RepositoryEntity


fun ProfileEntity.toProfileResponse(): ProfileResponse {
    return ProfileResponse(
        this.name,
        this.avatarUrl,
        this.email,
        this.followersCount,
        this.followingCount
    )
}

fun ProfileResponse.toProfileEntity(): ProfileEntity {
    return ProfileEntity(
        this.name,
        this.avatarUrl,
        this.email,
        this.followersCount,
        this.followingCount
    )
}

fun Edge.toRepoEntity(): RepositoryEntity {
    return RepositoryEntity(this.cursor, this.node.stargazerCount, this.node.name)
}

fun RepositoryEntity.toNode(): Node {
    return Node(this.name, this.stars)
}