package com.raditya.wikipedia.feature.login.di

import com.raditya.wikipedia.feature.login.data.LoginRepository
import com.raditya.wikipedia.feature.login.data.LoginRepositoryImpl
import com.raditya.wikipedia.feature.login.domain.LoginUseCase
import com.raditya.wikipedia.feature.login.domain.LoginUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class LoginModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindsRepository(impl: LoginRepositoryImpl): LoginRepository

    @ActivityRetainedScoped
    @Binds
    abstract fun bindsUseCase(impl: LoginUseCaseImpl): LoginUseCase
}