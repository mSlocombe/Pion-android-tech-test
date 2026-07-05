package com.github.mslocombe.pionandroidtechtest.ui.screen.photos

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.mslocombe.pionandroidtechtest.ui.screen.photos.content.ErrorContent
import com.github.mslocombe.pionandroidtechtest.ui.screen.photos.content.LoadingContent
import com.github.mslocombe.pionandroidtechtest.ui.screen.photos.content.ReadyContent

@Composable
fun PhotosScreen(
    viewModel: PhotosViewModelImpl = hiltViewModel<PhotosViewModelImpl>(),
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier
            .testTag("PhotosScreen")
            .fillMaxSize(),
        topBar = {
            // Box instead of a row to allow the title to be centered regardless of back button size
            Box(Modifier.fillMaxWidth().systemBarsPadding()) {
                BackButton(Modifier.align(Alignment.CenterStart)) { onBack() }
                Title(
                    Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 24.dp)
                )
            }
        },
        content = { scaffoldPadding ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .padding(horizontal = 28.dp)
            ) {
                AnimatedContent(
                    modifier = Modifier
                        .align(Alignment.Center),
                    targetState = uiState,
                    transitionSpec = { fadeIn() togetherWith fadeOut() },
                    contentAlignment = Alignment.Center,
                ) { currentState ->
                    when (currentState) {
                        PhotosScreenState.Loading -> {
                            LoadingContent()
                        }

                        is PhotosScreenState.Error -> {
                            val retryEnabled by currentState.retryEnabled.collectAsStateWithLifecycle()

                            ErrorContent(
                                retryEnabled = retryEnabled,
                                onRetry = {
                                    currentState.disableRetryButton()
                                    viewModel.retry()
                                }
                            )
                        }

                        is PhotosScreenState.Ready -> {
                            ReadyContent(currentState.cards)
                        }
                    }
                }
            }
        }
    )
}