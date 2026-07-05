package com.github.mslocombe.pionandroidtechtest.ui.screen.photos

sealed interface PhotosScreenState {
    data object Loading: PhotosScreenState
    data class Ready(val cards: List<PhotoCard>): PhotosScreenState
    data object Error: PhotosScreenState
}