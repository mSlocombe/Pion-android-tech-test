package com.github.mslocombe.pionandroidtechtest.navigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.github.mslocombe.pionandroidtechtest.HiltTestActivity
import com.github.mslocombe.pionandroidtechtest.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class AppNavHostTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val compose = createAndroidComposeRule<HiltTestActivity>()

    @Test
    fun photosBackNavigatesToLogin() {
        compose.setContent {
            AppNavHost(Photos)
        }

        compose.onNodeWithTag("PhotosScreen").assertIsDisplayed()
        val backLabel = compose.activity.getString(R.string.back)
        compose.onNodeWithContentDescription(backLabel).performClick()
        compose.waitForIdle()

        compose.onNodeWithTag("LoginScreen").assertIsDisplayed()
    }
}