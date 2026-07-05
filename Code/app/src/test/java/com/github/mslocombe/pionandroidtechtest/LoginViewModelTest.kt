package com.github.mslocombe.pionandroidtechtest

import com.github.mslocombe.pionandroidtechtest.ui.screen.login.LoginViewModelImpl
import org.junit.Test

class LoginViewModelTest {

    @Test
    fun emailShowsErrorAfterValidateLoginWhenEmpty() {
        val viewModel = LoginViewModelImpl()

        // No email set
        viewModel.validateLogin()
        assert(viewModel.uiState.value.emailState.showError)
    }

    @Test
    fun emailDoesNotShowAfterValidateLoginWhenEmpty() {
        val viewModel = LoginViewModelImpl()
        viewModel.updateEmail("ABC")

        viewModel.validateLogin()
        assert(!viewModel.uiState.value.emailState.showError)
    }

    @Test
    fun passwordErrorAfterValidateLoginWhenEmpty() {
        val viewModel = LoginViewModelImpl()

        // No email set
        viewModel.validateLogin()
        assert(viewModel.uiState.value.passwordState.showError)
    }

    @Test
    fun passwordDoesNotShowAfterValidateLoginWhenEmpty() {
        val viewModel = LoginViewModelImpl()
        viewModel.updatePassword("ABC")

        viewModel.validateLogin()
        assert(!viewModel.uiState.value.passwordState.showError)
    }

    @Test
    fun validEmailAndPasswordNavigatesToPhotos() {
        val viewModel = LoginViewModelImpl()
        viewModel.updateEmail("Set")
        viewModel.updatePassword("Set")

        viewModel.validateLogin()

        assert(viewModel.loginStatus.value == true)
    }
}