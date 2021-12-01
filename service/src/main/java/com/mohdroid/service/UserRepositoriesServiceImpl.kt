package com.mohdroid.service

import com.mohdroid.domain.common.Either
import com.mohdroid.domain.common.toRepoInfo
import com.mohdroid.domain.dto.repositories.UserRepositoryRequest
import com.mohdroid.domain.dto.repositories.service.RepositoryResponseService
import com.mohdroid.domain.dto.repositories.service.UserRepositoriesRequestService
import com.mohdroid.domain.enums.ErrorType
import com.mohdroid.domain.repository.UserRepositoriesRepo
import com.mohdroid.domain.result.Error
import com.mohdroid.domain.result.ServiceResult
import com.mohdroid.domain.service.UserRepositoriesService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoriesServiceImpl @Inject constructor(
    private val userRepositoriesRepo: UserRepositoriesRepo
) : UserRepositoriesService {

    override suspend fun getUserRepos(userRepositoriesRequestService: UserRepositoriesRequestService): ServiceResult<RepositoryResponseService> {
        if (userRepositoriesRequestService.limit <= 0 || userRepositoriesRequestService.limit > 10)
            return ServiceResult.Failure(Error(ErrorType.INVALID_COUNT_NUMBER))
        if (userRepositoriesRequestService.offset < 0)
            return ServiceResult.Failure(Error(ErrorType.INVALID_COUNT_NUMBER))

        val userRepositories = userRepositoriesRepo.getUserRepositories(
            UserRepositoryRequest(
                userRepositoriesRequestService.limit,
                userRepositoriesRequestService.offset
            )
        )
        return when (userRepositories) {
            is Either.Left<ErrorType> ->
                ServiceResult.Failure(Error(userRepositories.a))
            is Either.Right -> {
                ServiceResult.Success(
                    RepositoryResponseService(
                        userRepositories.b.totalCount,
                        userRepositories.b.edges.map { it.toRepoInfo() }.toMutableList()
                    )
                )
            }
        }
    }

}