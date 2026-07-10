package com.github.mslocombe.pionandroidtechtest.app2.ui.login

import jakarta.inject.Inject

class LoginValidatorImpl @Inject constructor(): LoginValidator {

    override fun validateLogin(email: String, password: String): LoginResult {
        return LoginResult(
            emailValid = email.isNotEmpty(),
            passwordValid = password.isNotEmpty()
        )
    }
}