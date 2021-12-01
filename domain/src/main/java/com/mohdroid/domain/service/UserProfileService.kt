package com.mohdroid.domain.service

import com.mohdroid.domain.dto.profile.service.ProfileResponseService
import com.mohdroid.domain.result.ServiceResult

interface UserProfileService {

    suspend fun getUserProfile() : ServiceResult<ProfileResponseService>
}