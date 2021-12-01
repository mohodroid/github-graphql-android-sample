package com.mohdroid.repository.network.networks.repositories

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.api.Input
import com.mohdroid.GetRepositoriesQuery
import com.mohdroid.domain.dto.repositories.UserRepositoryRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoriesNetworkImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : UserRepositoriesNetwork {

    override suspend fun getUserRepositories(first: Int, cursor: String?): ApolloQueryCall<GetRepositoriesQuery.Data> =
        apolloClient.query(GetRepositoriesQuery(first, Input.fromNullable(cursor)))


    /*
          This represents a nullable property of the response type,
           in this case this is of the type GetRepositoriesQuery.Data.
       */

    /*
    errors: This represents the errors that have been returned in the response.
     This is a nullable list, so if that list is empty or null then the request would have been successful
     */
    /*
    hasErrors: This is a convenience function which checks whether errors is null or empty.
     */


}