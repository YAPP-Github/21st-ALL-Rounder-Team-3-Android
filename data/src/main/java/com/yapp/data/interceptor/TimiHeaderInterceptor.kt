package com.yapp.data.interceptor

import okhttp3.Headers.Companion.toHeaders
import okhttp3.Interceptor
import okhttp3.Response

class TimiHeaderInterceptor : Interceptor {

//    private val accessToken: String
//        get() = preference.accessToken

    override fun intercept(chain: Interceptor.Chain): Response {
        val header = mapOf(
            AUTHORIZATION to "Bearer "
        )
        val builder = chain.request().newBuilder()
            .headers((header + chain.request().headers).toHeaders())

        return chain.proceed(builder.build())
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
    }
}
