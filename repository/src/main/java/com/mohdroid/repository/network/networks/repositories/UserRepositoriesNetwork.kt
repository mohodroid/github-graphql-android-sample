package com.mohdroid.repository.network.networks.repositories

import com.apollographql.apollo.ApolloQueryCall
import com.mohdroid.GetRepositoriesQuery


interface UserRepositoriesNetwork {

    suspend fun getUserRepositories(first: Int, cursor: String?)
            : ApolloQueryCall<GetRepositoriesQuery.Data>

}