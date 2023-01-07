package com.yapp.timitimi.data.di

import com.yapp.timitimi.data.repository.FeedbackRepositoryImpl
import com.yapp.timitimi.data.repository.ParticipantsRepositoryImpl
import com.yapp.timitimi.domain.respository.FeedbackRepository
import com.yapp.timitimi.domain.respository.ParticipantsRepository
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

