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

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Error

            if (_retryEnabled.value != other._retryEnabled.value) return false
            if (retryEnabled.value != other.retryEnabled.value) return false

            return true
        }

        override fun hashCode(): Int {
            var result = _retryEnabled.hashCode()
            result = 31 * result + retryEnabled.hashCode()
            return result
        }
    }
}