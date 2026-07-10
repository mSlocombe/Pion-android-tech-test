package com.github.mslocombe.pionandroidtechtest.app2.ui.login

import org.junit.Test

class LoginValidatorTest {

    @Test
    fun emptyEmailReturnsEmailError() {
        val validator = LoginValidatorImpl()

        val result = validator.validateLogin(email = "", password = "filled")

        assert(!result.emailValid)
    }

    @Test
    fun emptyPasswordReturnsPasswordError() {
        val validator = LoginValidatorImpl()

        val result = validator.validateLogin(email = "filled", password = "")

        assert(!result.passwordValid)
    }

    @Test
    fun filledEmailIsValid() {
        val validator = LoginValidatorImpl()

        val result = validator.validateLogin(email = "filled", password = "")

        assert(result.emailValid)
    }

    @Test
    fun filledPasswordIsValid() {
        val validator = LoginValidatorImpl()

        val result = validator.validateLogin(email = "", password = "filled")

        assert(result.passwordValid)
    }
}