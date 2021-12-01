package com.mohdroid.repository

import com.apollographql.apollo.exception.ApolloCanceledException
import com.apollographql.apollo.exception.ApolloHttpException
import com.apollographql.apollo.exception.ApolloNetworkException
import com.apollographql.apollo.exception.ApolloParseException
import com.mohdroid.domain.enums.ErrorType

object RepoErrorHandler {

     fun findErrorType(t: Exception): ErrorType {
        t.printStackTrace()
        if (t is ApolloNetworkException) {
            return ErrorType.IO_EXCEPTION
        }
        if (t is ApolloCanceledException) {
            return ErrorType.CANCELED_EXCEPTION
        }
        if (t is ApolloHttpException) {
            return ErrorType.HTTP_EXCEPTION
        }
        if (t is ApolloParseException) {
            return ErrorType.PARSE_EXCEPTION
        }
        return ErrorType.EXCEPTION
    }

}