package com.raditya.wikipedia.feature.login.di

import com.raditya.wikipedia.feature.login.data.LoginRepository
import com.raditya.wikipedia.feature.login.data.LoginRepositoryImpl
import com.raditya.wikipedia.feature.login.domain.LoginUseCase
import com.raditya.wikipedia.feature.login.domain.LoginUseCaseImpl
import com.raditya.wikipedia.feature.login.presentation.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
abstract class LoginModule {

    @FragmentScoped
    @Binds
    abstract fun bindsRepository(impl: LoginRepositoryImpl): LoginRepository

    @FragmentScoped
    @Binds
    abstract fun bindsUseCase(impl: LoginUseCaseImpl): LoginUseCase

    @Module
    @InstallIn(FragmentComponent::class)
    object ViewModel {
        @FragmentScoped
        @Provides
        fun providesVm(usecase: LoginUseCase) = LoginViewModel(usecase)
    }
}