package com.github.mslocombe.pionandroidtechtest.ui.screen.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor() : LoginViewModel, ViewModel() {

    private val _uiState = MutableStateFlow(LoginState())
    override val uiState = _uiState.asStateFlow()

    private val _loginStatus = MutableStateFlow(false)
    override val loginStatus = _loginStatus.asStateFlow()

    override fun updateEmail(newEmail: String) {
        _uiState.update { current ->
            current.copy(
                emailState = current.emailState.copy(text = newEmail)
            )
        }
    }

    override fun updatePassword(newPassword: String) {
        _uiState.update { current ->
            current.copy(
                passwordState = current.passwordState.copy(text = newPassword)
            )
        }
    }

    override fun validateLogin() {
        val emailValid = _uiState.value.emailState.text.isNotEmpty()
        val passwordValid = _uiState.value.passwordState.text.isNotEmpty()

        _loginStatus.update { emailValid && passwordValid }

        _uiState.update { current ->
            current.copy(
                emailState = current.emailState.copy(showError = !emailValid),
                passwordState = current.passwordState.copy(showError = !passwordValid)
            )
        }
    }
}