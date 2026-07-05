package com.github.mslocombe.pionandroidtechtest.ui.screen.photos.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.mslocombe.pionandroidtechtest.ui.screen.photos.PhotoCard
import com.github.mslocombe.pionandroidtechtest.ui.screen.photos.PhotoCardState
import kotlin.random.Random

@Composable
fun ReadyContent(
    modifier: Modifier = Modifier,
    cards: List<PhotoCardState>
) {
    LazyColumn(
        modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(bottom = 28.dp)
    ) {
        items(cards, key = { it.id }) {
            PhotoCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItem(), it
            )
        }
    }
}

@Preview
@Composable
private fun Preview_ReadyContent() {
    ReadyContent(
        cards = listOf(
            PhotoCardState(1, "Title #1", "", Color(Random.nextInt())),
            PhotoCardState(2, "Title #2", "", Color(Random.nextInt())),
            PhotoCardState(3, "Title #3", "", Color(Random.nextInt()))
        )
    )
}