package com.github.mslocombe.pionandroidtechtest.app2.ui.login

interface LoginValidator {
    fun validateLogin(email: String, password: String): LoginResult

}