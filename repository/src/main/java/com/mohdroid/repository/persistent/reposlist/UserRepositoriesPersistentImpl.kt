package com.mohdroid.repository.persistent.reposlist

import com.mohdroid.repository.entity.repos.RepositoryEntity
import com.mohdroid.repository.persistent.DataBase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoriesPersistentImpl @Inject constructor(
    private val dataBase: DataBase
) : UserRepositoriesPersistent {


    override suspend fun insertUserRepositories(reposEntity: List<RepositoryEntity>) {
        dataBase.reposDao().insert(reposEntity)
    }

    override suspend fun getUserRepositories(limit: Int, offset: Int): List<RepositoryEntity> {
      return dataBase.reposDao().getRepos(limit, offset)
    }

    override suspend fun getTotalCount(): Int {
        return dataBase.reposDao().getTotalCount()
    }

    override suspend fun getLastItem(): RepositoryEntity {
        return dataBase.reposDao().getLastItem()
    }

    override suspend fun updateRepository(toRepoEntity: RepositoryEntity) {
        return dataBase.reposDao().updateRepo(toRepoEntity)
    }



}