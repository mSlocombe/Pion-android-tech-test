package com.github.mslocombe.pionandroidtechtest.ui.screen.login

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.github.mslocombe.pionandroidtechtest.HiltTestActivity
import com.github.mslocombe.pionandroidtechtest.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class LoginScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val compose = createAndroidComposeRule<HiltTestActivity>()

    @Test
    fun loginScreenDisplayed() {
        compose.setContent {
            LoginScreen()
        }

        compose.onNodeWithTag("LoginScreen").assertIsDisplayed()
    }

    @Test
    fun welcomeMessageDisplayed() {
        compose.setContent {
            LoginScreen()
        }

        val label = compose.activity.getString(R.string.welcome_back)
        compose.onNodeWithText(label).assertIsDisplayed()
    }

    @Test
    fun loginDescriptionDisplayed() {
        compose.setContent {
            LoginScreen()
        }

        val label = compose.activity.getString(R.string.login_description)
        compose.onNodeWithText(label).assertIsDisplayed()
    }

    @Test
    fun loginButtonClickLogsIn() {
        var loggedIn = false
        val viewModel = LoginViewModelImpl()
        viewModel.updateEmail("ABC")
        viewModel.updatePassword("ABC")

        compose.setContent {
            LoginScreen(viewModel) {
                loggedIn = true
            }
        }

        val label = compose.activity.getString(R.string.log_in)
        compose.onNodeWithText(label).performClick()

        // Allow LaunchedEffect to trigger
        compose.waitForIdle()

        assert(loggedIn)
    }
}