package com.mohdroid.domain.repository

import com.mohdroid.domain.common.Either
import com.mohdroid.domain.dto.profile.ProfileResponse
import com.mohdroid.domain.enums.ErrorType

interface UserProfileRepo {

    suspend fun getProfileData() : Either<ErrorType, ProfileResponse>
}