package com.yapp.data.interceptor

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager

object TimiChunckerInterceptor {

    fun provide(context: Context): ChuckerInterceptor {
        return ChuckerInterceptor
            .Builder(context = context)
            .collector(
                ChuckerCollector(
                    context = context,
                    showNotification = true,
                    retentionPeriod = RetentionManager.Period.ONE_HOUR,
                )
            )
            .maxContentLength(250_000L)
            .alwaysReadResponseBody(true)
            .build()
    }
}
