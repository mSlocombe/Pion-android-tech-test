package com.github.mslocombe.pionandroidtechtest.ui.screen.login

import kotlinx.coroutines.flow.StateFlow

interface LoginViewModel {
    val uiState: StateFlow<LoginState>
    val loginStatus: StateFlow<Boolean>

    fun updateEmail(newEmail: String)
    fun updatePassword(newPassword: String)

    fun attemptLogin()
}