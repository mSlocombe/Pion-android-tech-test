package com.github.mslocombe.pionandroidtechtest

import com.github.mslocombe.pionandroidtechtest.ui.screen.login.LoginViewModelImpl
import org.junit.Test

class LoginViewModelTest {

    @Test
    fun emailShowsErrorAfterAttemptLoginWhenEmpty() {
        val viewModel = LoginViewModelImpl()

        // No email set
        viewModel.attemptLogin()
        assert(viewModel.uiState.value.emailState.showError)
    }

    @Test
    fun emailDoesNotShowAfterAttemptLoginWhenEmpty() {
        val viewModel = LoginViewModelImpl()
        viewModel.updateEmail("ABC")

        viewModel.attemptLogin()
        assert(!viewModel.uiState.value.emailState.showError)
    }

    @Test
    fun passwordErrorAfterAttemptLoginWhenEmpty() {
        val viewModel = LoginViewModelImpl()

        // No email set
        viewModel.attemptLogin()
        assert(viewModel.uiState.value.passwordState.showError)
    }

    @Test
    fun passwordDoesNotShowAfterAttemptLoginWhenEmpty() {
        val viewModel = LoginViewModelImpl()
        viewModel.updatePassword("ABC")

        viewModel.attemptLogin()
        assert(!viewModel.uiState.value.passwordState.showError)
    }

    @Test
    fun validEmailAndPasswordNavigatesToPhotos() {
        val viewModel = LoginViewModelImpl()
        viewModel.updateEmail("Set")
        viewModel.updatePassword("Set")

        viewModel.attemptLogin()

        assert(viewModel.loginStatus.value == true)
    }
}