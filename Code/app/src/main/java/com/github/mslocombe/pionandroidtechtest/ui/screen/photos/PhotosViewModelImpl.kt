package com.github.mslocombe.pionandroidtechtest.ui.screen.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class PhotosViewModelImpl @Inject constructor(
    private val getPhotoListUseCase: GetPhotoListUseCase
) : ViewModel() {
    private val _dataState = MutableStateFlow<PhotoListResult?>(null)


    private val _searchState = MutableStateFlow("")
    val searchState = _searchState.asStateFlow()

    @OptIn(FlowPreview::class)
    val uiState = combine(
        _dataState,
        _searchState.debounce { 300 }.distinctUntilChanged()
    ) { data, search ->
        when (data) {
            null -> {
                PhotosScreenState.Loading
            }

            PhotoListResult.Error -> {
                PhotosScreenState.Error()
            }

            is PhotoListResult.Success -> {
                if (search.isEmpty()) {
                    PhotosScreenState.Ready(data.cards)
                } else {
                    val filteredCards = withContext(Dispatchers.Default) {
                        data.cards.filter { it.title.lowercase().contains(search.lowercase()) }
                    }
                    PhotosScreenState.Ready(filteredCards)
                }
            }
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        PhotosScreenState.Loading
    )

    private var currentPhotoRequest: Job? = null

    init {
        requestPhotoList()
    }

    fun updateSearch(newSearch: String) {
        _searchState.update { newSearch }
    }

    private fun requestPhotoList() {
        if (currentPhotoRequest?.isActive == true) return

        currentPhotoRequest = viewModelScope.launch {
            when (val result = getPhotoListUseCase()) {
                PhotoListResult.Error -> {
                    _dataState.update { PhotoListResult.Error }
                }

                is PhotoListResult.Success -> {
                    _dataState.update {
                        PhotoListResult.Success(result.cards)
                    }
                }
            }
        }
    }

    fun retry() {
        _dataState.update { null }
        requestPhotoList()
    }
}