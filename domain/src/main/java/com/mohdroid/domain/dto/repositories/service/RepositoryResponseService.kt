package com.mohdroid.domain.dto.repositories.service

data class RepositoryResponseService(
    val count: Int,
    val repositories: MutableList<RepoInfo>,
)

data class RepoInfo(
    val name: String,
    val stars: Int,
)