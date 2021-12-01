package com.mohdroid.repository.features

import com.apollographql.apollo.coroutines.await
import com.mohdroid.GetProfileQuery
import com.mohdroid.domain.common.Either
import com.mohdroid.domain.dto.profile.ProfileResponse
import com.mohdroid.domain.enums.ErrorType
import com.mohdroid.domain.repository.UserProfileRepo
import com.mohdroid.repository.RepoErrorHandler.findErrorType
import com.mohdroid.repository.common.toProfileEntity
import com.mohdroid.repository.common.toProfileResponse
import com.mohdroid.repository.entity.profile.ProfileEntity
import com.mohdroid.repository.network.networks.profile.UserProfileNetwork
import com.mohdroid.repository.persistent.profile.UserProfilePersistent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepoImpl @Inject constructor(
    private val profileNetwork: UserProfileNetwork,
    private val profilePersistent: UserProfilePersistent
) : UserProfileRepo {

    override suspend fun getProfileData(): Either<ErrorType, ProfileResponse> {
        val local: ProfileEntity? = profilePersistent.getProfile()
        val response = try {
            profileNetwork.getProfile().await()
        } catch (e: Exception) {
            return returnEitherLeftOrRight(local, findErrorType(e))
        }
        val data = response.data
        if (data == null || response.hasErrors()) {
            return returnEitherLeftOrRight(local, ErrorType.EXCEPTION)
        }
        val profileResponse = getProfile(data)
        if (local == null)
            profilePersistent.insertProfile(profileResponse.toProfileEntity())
        else
            profilePersistent.update(profileResponse.toProfileEntity())
        return Either.Right(profileResponse)
    }

    private fun returnEitherLeftOrRight(
        local: ProfileEntity?,
        e: ErrorType
    ): Either<ErrorType, ProfileResponse> {
        return if (local == null)
            Either.Left(e)
        else Either.Right(local.toProfileResponse())
    }

    private fun getProfile(data: GetProfileQuery.Data): ProfileResponse {
        return ProfileResponse(
            data.viewer.name,
            data.viewer.avatarUrl.toString(),
            data.viewer.email,
            data.viewer.followers.totalCount,
            data.viewer.following.totalCount
        )
    }

}