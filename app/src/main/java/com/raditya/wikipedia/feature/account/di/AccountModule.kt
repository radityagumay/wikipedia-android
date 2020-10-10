package com.raditya.wikipedia.feature.account.di

import com.raditya.wikipedia.feature.account.data.AccountRepository
import com.raditya.wikipedia.feature.account.data.AccountRepositoryImpl
import com.raditya.wikipedia.feature.account.domain.AccountUseCase
import com.raditya.wikipedia.feature.account.domain.AccountUseCaseImpl
import com.raditya.wikipedia.feature.account.presentation.AccountViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
abstract class AccountModule {

    @Binds
    @FragmentScoped
    abstract fun bindsRepository(impl: AccountRepositoryImpl): AccountRepository

    @Binds
    @FragmentScoped
    abstract fun bindsUseCase(impl: AccountUseCaseImpl): AccountUseCase

    @Module
    @InstallIn(FragmentComponent::class)
    object ViewModel {

        @Provides
        @FragmentScoped
        fun providesVm(usecase: AccountUseCase) = AccountViewModel(usecase)
    }
}