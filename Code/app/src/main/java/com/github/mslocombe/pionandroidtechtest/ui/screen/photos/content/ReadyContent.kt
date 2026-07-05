package com.github.mslocombe.pionandroidtechtest.ui.screen.photos.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.mslocombe.pionandroidtechtest.R
import com.github.mslocombe.pionandroidtechtest.ui.component.OutlinedTextFieldThemed
import com.github.mslocombe.pionandroidtechtest.ui.screen.photos.PhotoCard
import com.github.mslocombe.pionandroidtechtest.ui.screen.photos.PhotoCardState
import com.github.mslocombe.pionandroidtechtest.ui.theme.SBTechincalTestTheme
import kotlin.random.Random

@Composable
fun ReadyContent(
    modifier: Modifier = Modifier,
    searchValue: String,
    onSearchChanged: (String) -> Unit,
    cards: List<PhotoCardState>
) {
    Column(modifier) {
        OutlinedTextFieldThemed(
            modifier = Modifier
                .fillMaxWidth(),
            label = stringResource(R.string.search),
            value = searchValue,
            onValueChange = onSearchChanged
        )

        LazyColumn(
            Modifier.weight(1f),
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
}

@Preview
@Composable
private fun Preview_ReadyContent() {
    SBTechincalTestTheme {
        ReadyContent(
            Modifier.fillMaxSize(),
            "Search Term",
            onSearchChanged = {},
            cards = listOf(
                PhotoCardState(1, "Title #1", "", Color(Random.nextInt())),
                PhotoCardState(2, "Title #2", "", Color(Random.nextInt())),
                PhotoCardState(3, "Title #3", "", Color(Random.nextInt()))
            )
        )
    }
}