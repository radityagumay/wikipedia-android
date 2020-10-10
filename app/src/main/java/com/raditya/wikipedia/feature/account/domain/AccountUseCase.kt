package com.raditya.wikipedia.feature.account.domain

import com.raditya.wikipedia.feature.account.data.AccountRepository
import okhttp3.MediaType
import java.io.File
import javax.inject.Inject

interface AccountUseCase {
    suspend fun execute(file: File, mediaType: MediaType)
}

class AccountUseCaseImpl @Inject constructor(
    private val repository: AccountRepository
) : AccountUseCase {
    override suspend fun execute(file: File, mediaType: MediaType) {
        repository.execute(file, mediaType)
    }
}