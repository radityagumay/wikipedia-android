package com.raditya.wikipedia.feature.login.presentation.intent

sealed class LoginIntent {
    object ClickIntent : LoginIntent()

    data class CredIntent(
        val email: String,
        val password: String
    ) : LoginIntent()
}