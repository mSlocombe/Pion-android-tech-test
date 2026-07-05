package com.github.mslocombe.pionandroidtechtest.ui.screen.photos.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mslocombe.pionandroidtechtest.ui.screen.photos.PhotoCard
import com.github.mslocombe.pionandroidtechtest.ui.screen.photos.PhotoCardState

@Composable
fun ReadyContent(cards: List<PhotoCardState>) {
    LazyColumn(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(bottom = 28.dp)
    ) {
        items(cards) {
            PhotoCard(it)
        }
    }
}