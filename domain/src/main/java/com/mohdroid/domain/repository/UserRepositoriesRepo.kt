package com.mohdroid.domain.repository

import com.mohdroid.domain.common.Either
import com.mohdroid.domain.dto.repositories.UserRepositoriesResponse
import com.mohdroid.domain.dto.repositories.UserRepositoryRequest
import com.mohdroid.domain.enums.ErrorType

interface UserRepositoriesRepo {

    suspend fun getUserRepositories(userRepositoryRequest: UserRepositoryRequest): Either<ErrorType, UserRepositoriesResponse>
}