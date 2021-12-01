package com.mohdroid.repository.persistent.reposlist

import androidx.room.*
import com.mohdroid.repository.entity.repos.RepositoryEntity

@Dao
interface UserReposDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(repositoryEntities: List<RepositoryEntity>)

    @Query("SELECT * FROM repository LIMIT :limit OFFSET :offset")
    suspend fun getRepos(limit: Int, offset: Int): List<RepositoryEntity>

    @Query("SELECT  COUNT(cursor) FROM repository")
    suspend fun getTotalCount(): Int

    @Update
    suspend fun updateRepo(entity: RepositoryEntity)

    @Query("SELECT * FROM repository ORDER BY cursor DESC LIMIT 1")
    suspend fun getLastItem() : RepositoryEntity

}