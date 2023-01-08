package com.yapp.timitimi.data.di

import android.content.Context
import com.yapp.timitimi.data.preference.UserPreferenceImpl
import com.yapp.timitimi.domain.preference.UserPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {
    @Provides
    @Singleton
    fun provideUserPreference(
        @ApplicationContext context: Context,
    ): UserPreference {
        return UserPreferenceImpl(context, "Timitimi")
    }
}