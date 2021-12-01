package com.mohdroid.repository.di.module

import com.apollographql.apollo.ApolloClient
import com.mohdroid.repository.di.qualifier.BaseUrl
import com.mohdroid.repository.di.qualifier.LogginInterceptor
import com.mohdroid.repository.di.qualifier.RequestHeader
import com.mohdroid.repository.network.interceptors.RequestHeaderInterceptor
import com.mohdroid.repository.network.networks.profile.ProfileNetworkImpl
import com.mohdroid.repository.network.networks.profile.UserProfileNetwork
import com.mohdroid.repository.network.networks.repositories.UserRepositoriesNetwork
import com.mohdroid.repository.network.networks.repositories.UserRepositoriesNetworkImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton


@Module(includes = [NetworkModule.BindModule::class])
class NetworkModule {

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = "https://api.github.com/graphql"

    @Provides
    @LogginInterceptor
    fun provideLoggingInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @RequestHeader
    fun provideRequestHeaderInterceptor(
    ): Interceptor {
        return RequestHeaderInterceptor()
    }

    @Singleton
    @Provides
    fun provideOkHttp(
        @LogginInterceptor loggingInterceptor: Interceptor,
        @RequestHeader headerInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideApolloClient(
        @BaseUrl baseurl: String,
        okHttpClient: OkHttpClient
    ): ApolloClient {
        return ApolloClient.builder()
            .serverUrl(baseurl)
            .okHttpClient(okHttpClient)
            .build()
    }

    @Module
    abstract class BindModule {

        @Binds
        abstract fun binsUserRepositoriesNetwork(userRepositoriesNetworkImpl: UserRepositoriesNetworkImpl): UserRepositoriesNetwork

        @Binds
        abstract fun binsProfileNetwork(profileNetworkImpl: ProfileNetworkImpl): UserProfileNetwork

    }

}


