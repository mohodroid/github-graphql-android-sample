package com.mohdroid.service

import com.mohdroid.domain.common.Either
import com.mohdroid.domain.common.toProfileResponseService
import com.mohdroid.domain.dto.profile.service.ProfileResponseService
import com.mohdroid.domain.repository.UserProfileRepo
import com.mohdroid.domain.result.Error
import com.mohdroid.domain.result.ServiceResult
import com.mohdroid.domain.service.UserProfileService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileServiceImpl @Inject constructor(
    private val profileRepo: UserProfileRepo
) : UserProfileService {

    override suspend fun getUserProfile(): ServiceResult<ProfileResponseService> {

        return when (val profileData = profileRepo.getProfileData()) {
            is Either.Left ->
                ServiceResult.Failure(Error(profileData.a))

            is Either.Right ->
                ServiceResult.Success(profileData.b.toProfileResponseService())
        }

    }

}