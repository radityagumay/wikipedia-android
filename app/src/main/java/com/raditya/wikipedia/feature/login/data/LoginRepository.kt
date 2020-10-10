package com.raditya.wikipedia.feature.login.data

import com.raditya.wikipedia.common.Either
import com.raditya.wikipedia.common.service.WikipediaService
import com.raditya.wikipedia.data.login.request.LoginRequest
import com.raditya.wikipedia.data.login.response.LoginResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

interface LoginRepository {
    suspend fun execute(request: LoginRequest): Either<LoginResponse>
}

class LoginRepositoryImpl @Inject constructor(
    private val service: WikipediaService
) : LoginRepository {

    override suspend fun execute(request: LoginRequest): Either<LoginResponse> {
        delay(1000)
        return runCatching {
            Either.Success(service.login(request))
        }.getOrElse {
            Either.Error(it)
        }
    }
}