package com.github.mslocombe.pionandroidtechtest.ui.screen.login

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.mslocombe.pionandroidtechtest.R
import com.github.mslocombe.pionandroidtechtest.ui.theme.SBTechincalTestTheme

private const val TAG = "LoginScreen"

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel<LoginViewModelImpl>()
) {
    Box(
        Modifier
            .testTag("loginScreen")
            .fillMaxSize()
            .imePadding()
            .padding(all = 48.dp)
    ) {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val loggedIn by viewModel.loginStatus.collectAsStateWithLifecycle()

        LaunchedEffect(loggedIn) {
            if (loggedIn) {
                Log.d(TAG, "Logged in")
            }
        }

        Column(
            Modifier
                .align(Alignment.Center)
        ) {
            Text(
                text = stringResource(R.string.welcome_back),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(R.string.login_description),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(24.dp))

            EmailField(Modifier.fillMaxWidth(), uiState.emailState, viewModel::updateEmail)

            PasswordField(Modifier.fillMaxWidth(), uiState.passwordState, viewModel::updatePassword)
        }

        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            content = {
                Text(
                    text = stringResource(R.string.log_in)
                )
            },
            onClick = {
                viewModel.attemptLogin()
            }
        )
    }
}

@Composable
private fun EmailField(
    modifier: Modifier = Modifier,
    state: LoginFieldState,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = state.text,
        onValueChange = onValueChanged,
        label = {
            Text(
                stringResource(R.string.email),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        isError = state.showError,
        supportingText = { if (state.showError) Text(stringResource(R.string.email_error)) },
        colors = OutlinedTextFieldDefaults.colors().copy(
            unfocusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        )
    )
}

@Composable
private fun PasswordField(
    modifier: Modifier = Modifier,
    state: LoginFieldState,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = state.text,
        onValueChange = onValueChanged,
        visualTransformation = PasswordVisualTransformation(),
        label = {
            Text(
                stringResource(R.string.password),
                style = MaterialTheme.typography.bodyMedium
            )
        },
        isError = state.showError,
        supportingText = { if (state.showError) Text(stringResource(R.string.password_error)) },
        colors = OutlinedTextFieldDefaults.colors().copy(
            unfocusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        )
    )
}

@Preview(backgroundColor = 0xFFFFFF, showBackground = true)
@Composable
private fun Preview_LoginScreen() {
    SBTechincalTestTheme {
        LoginScreen()
    }
}