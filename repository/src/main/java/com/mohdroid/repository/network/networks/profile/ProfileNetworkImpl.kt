package com.mohdroid.repository.network.networks.profile

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloQueryCall
import com.mohdroid.GetProfileQuery
import com.mohdroid.GetRepositoriesQuery
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileNetworkImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : UserProfileNetwork {

    override suspend fun getProfile(): ApolloQueryCall<GetProfileQuery.Data> {
       return apolloClient.query(GetProfileQuery())
    }
}