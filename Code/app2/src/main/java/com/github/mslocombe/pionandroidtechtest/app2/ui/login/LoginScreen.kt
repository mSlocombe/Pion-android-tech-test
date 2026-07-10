package com.github.mslocombe.pionandroidtechtest.app2.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.mslocombe.pionandroidtechtest.app2.R
import com.github.mslocombe.pionandroidtechtest.app2.ui.theme.SBTechincalTestTheme

data class EmailState(
    val textFieldState: TextFieldState = TextFieldState(),
    val isError: Boolean = false
)

@Composable
fun LoginScreen() {
    var emailState by remember {
        mutableStateOf(EmailState())
    }

    val passwordState = rememberTextFieldState()

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
                state = emailState.textFieldState,
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
            OutlinedSecureTextField(
                state = passwordState,
                placeholder = {
                    Text(
                        text = stringResource(R.string.password),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            )
        }

        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            onClick = {
                if (emailState.textFieldState.text.isEmpty()) {
                    emailState = emailState.copy(
                        isError = true
                    )
                }
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