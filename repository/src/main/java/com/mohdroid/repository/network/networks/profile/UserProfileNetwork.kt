package com.mohdroid.repository.network.networks.profile

import com.apollographql.apollo.ApolloQueryCall
import com.mohdroid.GetProfileQuery

interface UserProfileNetwork {

    suspend fun getProfile(): ApolloQueryCall<GetProfileQuery.Data>
}