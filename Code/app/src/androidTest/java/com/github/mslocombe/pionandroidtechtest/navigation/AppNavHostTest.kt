package com.github.mslocombe.pionandroidtechtest.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
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

    lateinit var navController: TestNavHostController

    fun setupNavHost(start: Route) {
        compose.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppNavHost(navController, start)
        }
    }

    @Test
    fun loginNavigatingToPhotos() {
        setupNavHost(Login)

        compose.onNodeWithTag("LoginScreen").assertIsDisplayed()

        val emailLabel = compose.activity.getString(R.string.email)
        compose.onNodeWithText(emailLabel).performTextInput("filled")

        val passwordLabel = compose.activity.getString(R.string.password)
        compose.onNodeWithText(passwordLabel).performTextInput("filled")

        val loginLabel = compose.activity.getString(R.string.log_in)
        compose.onNodeWithText(loginLabel).performClick()

        // let the login state LaunchedEffect trigger
        compose.waitForIdle()

        assert(navController.currentDestination?.hasRoute<Photos>()?: false)
    }

    @Test
    fun photosBackNavigatesToLogin() {
        setupNavHost(Photos)

        compose.onNodeWithTag("PhotosScreen").assertIsDisplayed()
        val backLabel = compose.activity.getString(R.string.back)
        compose.onNodeWithContentDescription(backLabel).performClick()
        compose.waitForIdle()

        assert(navController.currentDestination?.hasRoute<Login>()?: false)
    }
}