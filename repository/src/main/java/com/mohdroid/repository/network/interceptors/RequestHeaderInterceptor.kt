package com.mohdroid.repository.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class RequestHeaderInterceptor(private val apiHeader: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        val builder = chain?.request()?.newBuilder()
        builder?.addHeader("Authorization", "Bearer $apiHeader")
        val newRequest = builder?.build()
        return chain?.proceed(newRequest!!)!!
    }
}