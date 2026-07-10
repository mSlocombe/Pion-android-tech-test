package com.github.mslocombe.pionandroidtechtest.app2.login

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.github.mslocombe.pionandroidtechtest.HiltTestActivity
import com.github.mslocombe.pionandroidtechtest.app2.R
import com.github.mslocombe.pionandroidtechtest.app2.ui.login.LoginScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class LoginScreenTest {

    @get:Rule(order = 0)
    val hilt = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val compose = createAndroidComposeRule<HiltTestActivity>()

    @Test
    fun welcomeMessageDisplays() {
        compose.setContent {
            LoginScreen()
        }

        val label = compose.activity.getString(R.string.welcome)
        compose.onNodeWithText(label).assertIsDisplayed()
    }

    @Test
    fun loginDescriptionDisplays() {
        compose.setContent {
            LoginScreen()
        }

        val label = compose.activity.getString(R.string.welcome_description)
        compose.onNodeWithText(label).assertIsDisplayed()
    }

    @Test
    fun emailFieldDisplays() {
        compose.setContent {
            LoginScreen()
        }

        val label = compose.activity.getString(R.string.email)
        compose.onNodeWithText(label).assertIsDisplayed()
    }

    @Test
    fun passwordFieldDisplays() {
        compose.setContent {
            LoginScreen()
        }

        val label = compose.activity.getString(R.string.password)
        compose.onNodeWithText(label).assertIsDisplayed()
    }

    // login button displays
    @Test
    fun loginButtonDisplays() {
        compose.setContent {
            LoginScreen()
        }

        val label = compose.activity.getString(R.string.log_in)
        compose.onNodeWithText(label).assertIsDisplayed()
    }

    @Test
    fun emailEmptyTextDisplaysWhenLoggingInWithEmptyEmail() {
        compose.setContent {
            LoginScreen()
        }

        val label = compose.activity.getString(R.string.log_in)
        compose.onNodeWithText(label).performClick()

        val errorLabel = compose.activity.getString(R.string.email_empty_error)
        compose.onNodeWithText(errorLabel).assertIsDisplayed()
    }
}
