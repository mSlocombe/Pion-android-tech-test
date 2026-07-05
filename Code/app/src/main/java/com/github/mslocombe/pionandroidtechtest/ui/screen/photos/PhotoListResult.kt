package com.github.mslocombe.pionandroidtechtest.ui.screen.photos

sealed interface PhotoListResult {
    data class Success(val cards: List<PhotoCardState>): PhotoListResult
    data object Error: PhotoListResult
}