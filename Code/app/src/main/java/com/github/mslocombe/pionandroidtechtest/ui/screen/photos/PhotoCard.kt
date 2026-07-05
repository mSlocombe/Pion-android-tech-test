package com.github.mslocombe.pionandroidtechtest.ui.screen.photos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlin.random.Random

@Composable
fun PhotoCard(
    state: PhotoCardState
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(Random.nextInt())),
            model = state.thumbnail,
            contentDescription = null,
            contentScale = ContentScale.FillHeight
        )
        Spacer(Modifier.width(28.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = state.title,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
private fun Preview_PhotoCard() {
    PhotoCard(
        state = PhotoCardState("", "Placeholder text fdksld fjkdsjflsd kfdjrei iocedjc wncwipjcmk cnqwpiocjd ceomci ncewpc cniewpcanc nciewoceaw")
    )
}