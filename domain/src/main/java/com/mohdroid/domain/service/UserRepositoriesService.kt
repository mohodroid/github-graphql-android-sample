package com.mohdroid.domain.service

import com.mohdroid.domain.dto.repositories.service.RepositoryResponseService
import com.mohdroid.domain.dto.repositories.service.UserRepositoriesRequestService
import com.mohdroid.domain.result.ServiceResult

interface UserRepositoriesService {

    suspend fun getUserRepos(userRepositoriesRequestService: UserRepositoriesRequestService): ServiceResult<RepositoryResponseService>

}