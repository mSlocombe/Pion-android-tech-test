package com.github.mslocombe.pionandroidtechtest.ui.screen.login

data class LoginState(
    val emailState: LoginFieldState = LoginFieldState("", false),
    val passwordState: LoginFieldState = LoginFieldState("", false)
)
