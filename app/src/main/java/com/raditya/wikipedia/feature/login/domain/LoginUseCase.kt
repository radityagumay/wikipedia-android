package com.raditya.wikipedia.feature.login.domain

import com.raditya.wikipedia.common.Either
import com.raditya.wikipedia.data.login.request.LoginRequest
import com.raditya.wikipedia.feature.login.data.LoginRepository
import javax.inject.Inject

interface LoginUseCase {
    suspend fun execute(email: String, password: String): Boolean
}

class LoginUseCaseImpl @Inject constructor(
    private val repository: LoginRepository
) : LoginUseCase {

    override suspend fun execute(email: String, password: String): Boolean {
        val request = LoginRequest(email, password)
        return repository.execute(request) is Either.Success
    }
}