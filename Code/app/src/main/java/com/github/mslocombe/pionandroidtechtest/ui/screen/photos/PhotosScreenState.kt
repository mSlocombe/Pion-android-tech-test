package com.github.mslocombe.pionandroidtechtest.ui.screen.photos

sealed interface PhotosScreenState {
    data object Loading: PhotosScreenState
}