package com.raditya.wikipedia.feature.login.presentation

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raditya.wikipedia.extension.isValidEmail
import com.raditya.wikipedia.feature.login.domain.LoginUseCase
import com.raditya.wikipedia.feature.login.presentation.intent.LoginIntent
import com.raditya.wikipedia.feature.login.presentation.intent.LoginIntent.ClickIntent
import com.raditya.wikipedia.feature.login.presentation.intent.LoginIntent.CredIntent
import com.raditya.wikipedia.feature.login.presentation.state.LoginState
import com.raditya.wikipedia.feature.login.presentation.state.LoginState.PrimaryState.LoginInfo
import com.raditya.wikipedia.feature.login.presentation.state.LoginState.SecondaryState.EmailError
import com.raditya.wikipedia.feature.login.presentation.state.LoginState.SecondaryState.Loading
import com.raditya.wikipedia.feature.login.presentation.state.LoginState.SecondaryState.LoginError
import com.raditya.wikipedia.feature.login.presentation.state.LoginState.SecondaryState.LoginSuccess
import com.raditya.wikipedia.feature.login.presentation.state.LoginState.SecondaryState.PasswordError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val usecase: LoginUseCase
) : ViewModel() {

    private var loginInfo: LoginInfo? = null

    private val _states = MutableLiveData<LoginState>()
    val state: LiveData<LoginState>
        get() = _states

    fun processIntents(flows: Flow<LoginIntent>, scope: LifecycleCoroutineScope) {
        flows.onEach { intent ->
            when (intent) {
                is ClickIntent -> clickIntent()
                is CredIntent -> credIntent(intent)
            }
        }.launchIn(scope)
    }

    private fun credIntent(intent: CredIntent) {
        if (isEmailAndPasswordBlank(intent)) return

        loginInfo = LoginInfo(intent.email, intent.password)
    }

    private fun clickIntent() {
        if (isInfoNull()) return
        if (isEmailEmptyOrInvalid()) return
        if (isPasswordEmpty()) return

        execute()
    }

    private fun isEmailAndPasswordBlank(intent: CredIntent): Boolean {
        if (intent.email.isBlank() && intent.password.isBlank()) {
            loginInfo = null
            return true
        }
        return false
    }

    private fun isInfoNull(): Boolean {
        if (loginInfo == null) {
            EmailError("Email is empty").also(_states::setValue)
            PasswordError("Password is empty").also(_states::setValue)
            return true
        }
        return false
    }

    private fun isEmailEmptyOrInvalid(): Boolean {
        if (loginInfo?.email?.isBlank() == true) {
            EmailError("Email is empty").also(_states::setValue)
            return true
        } else {
            if (loginInfo?.email!!.isValidEmail().not()) {
                EmailError("Email is not valid").also(_states::setValue)
                return true
            }
        }
        return false
    }

    private fun isPasswordEmpty(): Boolean {
        if (loginInfo?.password?.isBlank() == true) {
            PasswordError("Password is empty").also(_states::setValue)
            return true
        }
        return false
    }

    private fun execute() {
        viewModelScope.launch {
            Loading.also(_states::setValue)

            val email = loginInfo!!.email
            val password = loginInfo!!.password
            val isSuccess = usecase.execute(email, password)
            if (isSuccess) {
                LoginSuccess.also(_states::setValue).also {
                    _states.value = null
                }
            } else {
                LoginError("api return false").also(_states::setValue)
            }
        }
    }
}
