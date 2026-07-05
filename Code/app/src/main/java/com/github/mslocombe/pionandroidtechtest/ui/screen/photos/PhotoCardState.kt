package com.github.mslocombe.pionandroidtechtest.ui.screen.photos

import androidx.compose.ui.graphics.Color

data class PhotoCardState(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val background: Color
)
