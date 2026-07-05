package com.github.mslocombe.pionandroidtechtest.ui.screen.photos

import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed interface PhotosScreenState {

    object Loading: PhotosScreenState


    data class Ready(val cards: List<PhotoCardState>): PhotosScreenState

    @Stable
    class Error: PhotosScreenState {
        private val _retryEnabled = MutableStateFlow(true)
        val retryEnabled = _retryEnabled.asStateFlow()

        fun disableRetryButton() {
            _retryEnabled.update { false }
        }
    }
}