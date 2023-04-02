package com.yapp.timitimi.data.interceptor

import com.yapp.timitimi.domain.preference.UserPreference
import okhttp3.Headers.Companion.toHeaders
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber

class TimiHeaderInterceptor(
    private val preference: UserPreference
) : Interceptor {

    private val accessToken: String
        get() = preference.accessToken

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val builder = chain.request().newBuilder()

        if (isRequiredHeader(originalRequest)) {
            val header = mapOf(
                AUTHORIZATION to "Bearer $accessToken"
            )
            builder.headers((header + chain.request().headers).toHeaders())
        }

        return chain.proceed(builder.build())
    }

    private fun isRequiredHeader(request: Request): Boolean {
        // Check if the current API requires headers
        val path = request.url.encodedPath
        Timber.e("path is ${request.url.encodedPath}")
        return "/auth/sign-in" != path
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
    }
}
