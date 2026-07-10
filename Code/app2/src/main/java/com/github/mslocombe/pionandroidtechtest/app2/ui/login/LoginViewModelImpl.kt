package com.github.mslocombe.pionandroidtechtest.app2.ui.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


@HiltViewModel
class LoginViewModelImpl @Inject constructor(
    private val loginValidator: LoginValidator
): ViewModel() {

    private val _emailState = MutableStateFlow(LoginFieldState())
    val emailState = _emailState.asStateFlow()

    private val _passwordState = MutableStateFlow(LoginFieldState())
    val passwordState = _passwordState.asStateFlow()

    private val _loginStatus = MutableStateFlow(false)
    val loginStatus = _loginStatus.asStateFlow()

    fun updateEmail(newEmail: String) {
        _emailState.update {
            it.copy(textFieldState = newEmail)
        }
    }

    fun updatePassword(newPassword: String) {
        _passwordState.update {
            it.copy(textFieldState = newPassword)
        }
    }

    fun attemptLogin() {
        val validationResult = loginValidator.validateLogin(
            _emailState.value.textFieldState,
            _passwordState.value.textFieldState
        )

        _emailState.update {
            it.copy(isError = !validationResult.emailValid)
        }

        _passwordState.update {
            it.copy(isError = !validationResult.passwordValid)
        }

        _loginStatus.update { validationResult.emailValid && validationResult.passwordValid }
    }
}