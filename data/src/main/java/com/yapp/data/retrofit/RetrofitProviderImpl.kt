package com.yapp.data.retrofit

import com.yapp.data.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

class RetrofitProviderImpl(
    private val okHttpClient: OkHttpClient,
    private val jsonConverterFactory: Converter.Factory
) : RetrofitProvider {

    override fun provide(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(jsonConverterFactory)
            .build()
    }

    override fun provide(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(jsonConverterFactory)
            .build()
    }
}
