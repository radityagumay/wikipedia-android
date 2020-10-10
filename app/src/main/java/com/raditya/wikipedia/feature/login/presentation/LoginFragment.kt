@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.raditya.wikipedia.feature.login.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import com.raditya.wikipedia.R
import com.raditya.wikipedia.extension.goTo
import com.raditya.wikipedia.extension.throttleFirst
import com.raditya.wikipedia.feature.login.presentation.LoginFragmentDirections.Companion.actionLoginDestToAccountDest
import com.raditya.wikipedia.feature.login.presentation.intent.LoginIntent
import com.raditya.wikipedia.feature.login.presentation.intent.LoginIntent.ClickIntent
import com.raditya.wikipedia.feature.login.presentation.intent.LoginIntent.CredIntent
import com.raditya.wikipedia.feature.login.presentation.state.LoginState
import com.raditya.wikipedia.feature.login.presentation.state.LoginState.SecondaryState
import com.raditya.wikipedia.feature.login.presentation.state.LoginState.SecondaryState.EmailError
import com.raditya.wikipedia.feature.login.presentation.state.LoginState.SecondaryState.Loading
import com.raditya.wikipedia.feature.login.presentation.state.LoginState.SecondaryState.LoginError
import com.raditya.wikipedia.feature.login.presentation.state.LoginState.SecondaryState.LoginSuccess
import com.raditya.wikipedia.feature.login.presentation.state.LoginState.SecondaryState.PasswordError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import reactivecircus.flowbinding.android.view.clicks
import reactivecircus.flowbinding.android.widget.afterTextChanges
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    @Inject
    internal lateinit var vm: LoginViewModel

    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val flows = setupView(view)
        vm.state.observe(viewLifecycleOwner, ::states)
        vm.processIntents(flows, lifecycleScope)
    }

    private fun setupView(view: View): Flow<LoginIntent> {
        val email = view.findViewById<TextInputEditText>(R.id.etEmail)
            .also { etEmail = it }
            .afterTextChanges()
        val password = view.findViewById<TextInputEditText>(R.id.etPassword)
            .also { etPassword = it }
            .afterTextChanges()

        val credential = email.combine(password) { e, p ->
            CredIntent(e.view.text.toString(), p.view.text.toString())
        }

        val clicks = view.findViewById<Button>(R.id.btnSubmit)
            .clicks()
            .throttleFirst(1000).map { ClickIntent }

        return merge(credential, clicks)
    }

    private fun states(state: LoginState?) {
        when (state) {
            is SecondaryState -> {
                when (state) {
                    is PasswordError -> etPassword.error = state.message
                    is EmailError -> etEmail.error = state.message
                    is LoginSuccess -> {
                        goTo(actionLoginDestToAccountDest(
                            etEmail.text.toString(),
                            etPassword.text.toString()
                        ))
                    }
                    is LoginError -> Toast.makeText(activity, "error...", Toast.LENGTH_SHORT).show()
                    is Loading -> Toast.makeText(activity, "loading...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
