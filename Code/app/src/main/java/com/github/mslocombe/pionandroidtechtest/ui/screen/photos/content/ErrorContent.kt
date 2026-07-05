package com.github.mslocombe.pionandroidtechtest.ui.screen.photos.content

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.github.mslocombe.pionandroidtechtest.R

@Composable
fun ErrorContent(
    retryEnabled: Boolean,
    onRetry: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painterResource(R.drawable.ic_error),
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.error_description)
        )
        Button(
            enabled = retryEnabled,
            onClick = { onRetry() },
        ) {
            Text(
                text = stringResource(R.string.retry)
            )
        }
    }
}