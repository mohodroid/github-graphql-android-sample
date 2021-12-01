package com.mohdroid.repository.persistent.reposlist

import com.mohdroid.repository.entity.repos.RepositoryEntity

interface UserRepositoriesPersistent {

    suspend fun insertUserRepositories(reposEntity: List<RepositoryEntity>)

    suspend fun getUserRepositories(limit: Int, offset: Int): List<RepositoryEntity>

    suspend fun getTotalCount(): Int

    suspend fun getLastItem(): RepositoryEntity

    suspend fun updateRepository(toRepoEntity: RepositoryEntity)


}