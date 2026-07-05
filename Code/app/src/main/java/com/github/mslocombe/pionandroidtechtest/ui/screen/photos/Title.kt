package com.github.mslocombe.pionandroidtechtest.ui.screen.photos

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.github.mslocombe.pionandroidtechtest.R

@Composable
fun Title(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(R.string.photos_title),
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Ellipsis
    )
}