package com.github.mslocombe.pionandroidtechtest.ui.screen.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class PhotosViewModelImpl @Inject constructor(
    private val getPhotoListUseCase: GetPhotoListUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<PhotosScreenState>(PhotosScreenState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            when(val result = getPhotoListUseCase()) {
                PhotoListResult.Error -> { _uiState.update { PhotosScreenState.Error } }
                is PhotoListResult.Success -> {
                    _uiState.update {
                        PhotosScreenState.Ready(result.cards)
                    }
                }
            }
        }
    }
}