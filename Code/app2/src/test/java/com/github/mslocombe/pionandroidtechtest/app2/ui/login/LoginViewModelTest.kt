package com.github.mslocombe.pionandroidtechtest.app2.ui.login

import io.mockk.every
import io.mockk.mockk
import org.junit.Test

class LoginViewModelTest {

    @Test
    fun emailErrorStateWhenInvalid() {
        val mockValidator = mockk<LoginValidator>().apply {
            every { validateLogin("", "") } returns LoginResult(
                emailValid = false, passwordValid = false
            )
        }
        val viewModel = LoginViewModelImpl(mockValidator)

        viewModel.attemptLogin()

        assert(viewModel.emailState.value.isError)
    }

    @Test
    fun emailValidStateWhenValid() {
        val mockValidator = mockk<LoginValidator>().apply {
            every { validateLogin("", "") } returns LoginResult(
                emailValid = true, passwordValid = false
            )
        }
        val viewModel = LoginViewModelImpl(mockValidator)

        viewModel.attemptLogin()

        assert(!viewModel.emailState.value.isError)
    }

    @Test
    fun passwordErrorStateWhenInvalid() {
        val mockValidator = mockk<LoginValidator>().apply {
            every { validateLogin("", "") } returns LoginResult(
                emailValid = false, passwordValid = false
            )
        }
        val viewModel = LoginViewModelImpl(mockValidator)

        viewModel.attemptLogin()

        assert(viewModel.passwordState.value.isError)
    }

    @Test
    fun passwordValidStateWhenValid() {
        val mockValidator = mockk<LoginValidator>().apply {
            every { validateLogin("", "") } returns LoginResult(
                emailValid = false, passwordValid = true
            )
        }
        val viewModel = LoginViewModelImpl(mockValidator)

        viewModel.attemptLogin()

        assert(!viewModel.passwordState.value.isError)
    }

    @Test
    fun successfulLoginTriggersLoginEvent() {
        val mockValidator = mockk<LoginValidator>().apply {
            every { validateLogin("", "") } returns LoginResult(
                emailValid = true,
                passwordValid = true
            )
        }

        val viewModel = LoginViewModelImpl(mockValidator)

        viewModel.attemptLogin()

        assert(viewModel.loginStatus.value)
    }
}