package com.yapp.data.di

import com.yapp.data.repository.FeedbackRepositoryImpl
import com.yapp.data.repository.ParticipantsRepositoryImpl
import com.yapp.domain.respository.FeedbackRepository
import com.yapp.domain.respository.ParticipantsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsFeedbackRepositoryImpl(
        feedbackRepositoryImpl: FeedbackRepositoryImpl,
    ): FeedbackRepository

    @Binds
    @Singleton
    abstract fun bindsParticipantsRepositoryImpl(
        participantsRepositoryImpl: ParticipantsRepositoryImpl,
    ): ParticipantsRepository
}

