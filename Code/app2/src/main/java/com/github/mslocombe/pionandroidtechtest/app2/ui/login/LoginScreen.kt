package com.github.mslocombe.pionandroidtechtest.app2.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.mslocombe.pionandroidtechtest.app2.R
import com.github.mslocombe.pionandroidtechtest.app2.ui.theme.SBTechincalTestTheme

@Composable
fun LoginScreen(
    viewModel: LoginViewModelImpl = hiltViewModel<LoginViewModelImpl>()
) {
    val emailState by viewModel.emailState.collectAsStateWithLifecycle()
    val passwordState by viewModel.passwordState.collectAsStateWithLifecycle()

    Box(Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                text = stringResource(R.string.welcome),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(R.string.welcome_description),
                style = MaterialTheme.typography.titleMedium
            )
            OutlinedTextField(
                value = emailState.textFieldState,
                onValueChange = viewModel::updateEmail,
                isError = emailState.isError,
                placeholder = {
                    Text(
                        text = stringResource(R.string.email),
                        style = MaterialTheme.typography.labelMedium
                    )
                },
                supportingText = {
                    if (emailState.isError) {
                        Text(
                            text = stringResource(R.string.email_empty_error),
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            )
            OutlinedTextField(
                value = passwordState.textFieldState,
                onValueChange = viewModel::updatePassword,
                isError = passwordState.isError,
                visualTransformation = PasswordVisualTransformation(),
                placeholder = {
                    Text(
                        text = stringResource(R.string.password),
                        style = MaterialTheme.typography.labelMedium
                    )
                },
                supportingText = {
                    if (passwordState.isError) {
                        Text(
                            text = stringResource(R.string.password_empty_error),
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            )
        }

        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            onClick = {
                viewModel.attemptLogin()
            }
        ) {
            Text(
                text = stringResource(R.string.log_in),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview
@Composable
private fun Preview_LoginScreen() {
    SBTechincalTestTheme {
        LoginScreen()
    }
}