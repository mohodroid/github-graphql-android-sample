package com.mohdroid.repository.features

import com.apollographql.apollo.coroutines.await
import com.mohdroid.GetRepositoriesQuery
import com.mohdroid.domain.common.Either
import com.mohdroid.domain.dto.repositories.Edge
import com.mohdroid.domain.dto.repositories.Node
import com.mohdroid.domain.dto.repositories.UserRepositoriesResponse
import com.mohdroid.domain.dto.repositories.UserRepositoryRequest
import com.mohdroid.domain.enums.ErrorType
import com.mohdroid.domain.repository.UserRepositoriesRepo
import com.mohdroid.repository.RepoErrorHandler.findErrorType
import com.mohdroid.repository.common.toNode
import com.mohdroid.repository.common.toRepoEntity
import com.mohdroid.repository.entity.repos.RepositoryEntity
import com.mohdroid.repository.network.networks.repositories.UserRepositoriesNetwork
import com.mohdroid.repository.persistent.reposlist.UserRepositoriesPersistent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoriesRepoImpl @Inject constructor(
    private val userRepositoriesNetwork: UserRepositoriesNetwork,
    private val userRepositoriesPersistent: UserRepositoriesPersistent
) : UserRepositoriesRepo {

    override suspend fun getUserRepositories(userRepositoryRequest: UserRepositoryRequest): Either<ErrorType, UserRepositoriesResponse> {
        val local: List<RepositoryEntity> = userRepositoriesPersistent.getUserRepositories(
            userRepositoryRequest.limit,
            userRepositoryRequest.offset
        )
        val offset = userRepositoryRequest.offset - 1
        val cursor = if (offset > 0) getLastCursorFromDb(offset) else null
        val response = try {
            userRepositoriesNetwork.getUserRepositories(userRepositoryRequest.limit, cursor).await()
        } catch (e: Exception) {
            return returnEitherLeftOrRight(local, findErrorType(e))
        }
        val data = response.data
        if (data == null || response.hasErrors()) {
            return returnEitherLeftOrRight(local, ErrorType.EXCEPTION)
        }
        val edges = data.viewer.repositories.edges
        if (edges.isNullOrEmpty()) {
            return returnEitherRight(0, mutableListOf())
        }
        val edgesList = createEdges(edges)
        insertOrUpdateRepos(createEdges(edges), local)
        return returnEitherRight(data.viewer.repositories.totalCount,
            edgesList.map { it.node }.toList()
        )

    }

    private suspend fun insertOrUpdateRepos(edges: List<Edge>, local: List<RepositoryEntity>) {
        if (local.isNullOrEmpty())
            userRepositoriesPersistent.insertUserRepositories(edges.map { it.toRepoEntity() }
                .toList())
        else updateRepos(edges)
    }

    private suspend fun updateRepos(edges: List<Edge>) {
        edges.forEach {
            userRepositoriesPersistent.updateRepository(it.toRepoEntity())
        }
    }

    private suspend fun returnEitherLeftOrRight(
        local: List<RepositoryEntity>,
        errorType: ErrorType
    ): Either<ErrorType, UserRepositoriesResponse> {
        if (local.isEmpty())
            return returnEitherLeft(errorType)
        return returnEitherRight(
            userRepositoriesPersistent.getTotalCount(),
            local.map { it.toNode() }.toList()
        )
    }

    private fun returnEitherRight(
        count: Int,
        nodes: List<Node>
    ): Either.Right<UserRepositoriesResponse> {
        return Either.Right(UserRepositoriesResponse(count, nodes))
    }

    private fun returnEitherLeft(errorType: ErrorType): Either.Left<ErrorType> {
        return Either.Left(errorType)
    }

    private suspend fun getLastCursorFromDb(offset: Int): String? {
        val userRepositories = userRepositoriesPersistent.getUserRepositories(1, offset)
        return if (userRepositories.isNullOrEmpty()) null else userRepositories.last().cursor

    }

    private fun createEdges(edges: List<GetRepositoriesQuery.Edge?>) =
        edges.map {
            Edge(it!!.cursor, Node(it.node?.name, it.node?.stargazerCount))
        }

}