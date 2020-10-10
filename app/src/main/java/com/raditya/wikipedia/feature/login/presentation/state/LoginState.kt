package com.raditya.wikipedia.feature.login.presentation.state

sealed class LoginState {
    sealed class PrimaryState : LoginState() {
        data class LoginInfo(
            val email: String,
            val password: String
        ) : PrimaryState()
    }

    sealed class SecondaryState : LoginState() {
        data class EmailError(val message: String) : SecondaryState()
        data class PasswordError(val message: String) : SecondaryState()
        data class LoginError(
            val message: String
        ) : SecondaryState()

        object LoginSuccess : SecondaryState()
        object Loading : SecondaryState()
    }
}