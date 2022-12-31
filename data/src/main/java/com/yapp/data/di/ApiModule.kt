package com.yapp.data.di

import com.yapp.data.api.TimiServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideAddressApi(retrofit: Retrofit): TimiServiceApi {
        return retrofit.create(TimiServiceApi::class.java)
    }
}
