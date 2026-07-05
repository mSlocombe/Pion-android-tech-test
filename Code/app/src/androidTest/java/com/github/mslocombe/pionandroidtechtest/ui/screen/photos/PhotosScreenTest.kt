package com.github.mslocombe.pionandroidtechtest.ui.screen.photos

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.github.mslocombe.pionandroidtechtest.R
import com.github.mslocombe.pionandroidtechtest.hilt.ProvideGetPhotoListUseCase
import org.junit.Rule
import org.junit.Test

class PhotosScreenTest {

    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    @Composable
    private fun TestPhotosScreen(
        viewModel: PhotosViewModelImpl = PhotosViewModelImpl(ProvideGetPhotoListUseCase().provide()),
        onBack: () -> Unit = {}
    ) {
        PhotosScreen(
            viewModel = viewModel,
            onBack = onBack
        )
    }

    @Test
    fun backButtonDisplayed() {
        compose.setContent {
            TestPhotosScreen()
        }

        val label = compose.activity.getString(R.string.back)
        compose.onNodeWithContentDescription(label).assertIsDisplayed()
    }

    @Test
    fun backButtonClicked() {
        var backClicked = false
        compose.setContent {
            TestPhotosScreen(
                onBack = { backClicked = true }
            )
        }

        val label = compose.activity.getString(R.string.back)
        compose.onNodeWithContentDescription(label).performClick()
        assert(backClicked)
    }

    @Test
    fun titleDisplayed() {
        compose.setContent {
            TestPhotosScreen()
        }

        val label = compose.activity.getString(R.string.photos_title)
        compose.onNodeWithText(label).assertIsDisplayed()
    }
}