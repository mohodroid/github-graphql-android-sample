package com.mohdroid.repository.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class RequestHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        val builder = chain?.request()?.newBuilder()
        builder?.addHeader("Authorization", "Bearer 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")
        val newRequest = builder?.build()
        return chain?.proceed(newRequest!!)!!
    }
}