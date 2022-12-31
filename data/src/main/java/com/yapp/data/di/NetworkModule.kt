package com.yapp.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.yapp.data.BuildConfig
import com.yapp.data.interceptor.TimiChunckerInterceptor
import com.yapp.data.interceptor.TimiHeaderInterceptor
import com.yapp.data.retrofit.RetrofitProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    annotation class JsonConverter

    @Provides
    @Singleton
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return TimiChunckerInterceptor.provide(context)
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(
        //todo add userRepository
    ): TimiHeaderInterceptor {
        return TimiHeaderInterceptor()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        chuckerInterceptor: ChuckerInterceptor,
        headerInterceptor: TimiHeaderInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .addNetworkInterceptor(chuckerInterceptor)
            .addInterceptor(headerInterceptor)
            .readTimeout(30L, TimeUnit.SECONDS)
            .writeTimeout(30L, TimeUnit.SECONDS)
            .connectTimeout(30L, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @JsonConverter
    @Singleton
    fun provideJsonConverterFactory(): Converter.Factory {
        return Json {
            isLenient = true
            ignoreUnknownKeys = true
            coerceInputValues = true
        }.asConverterFactory("application/json".toMediaType())
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @JsonConverter jsonConverterFactory: Converter.Factory
    ): Retrofit {
        val provider = RetrofitProviderImpl(
            okHttpClient = okHttpClient,
            jsonConverterFactory = jsonConverterFactory
        )

        return provider.provide()
    }
}
