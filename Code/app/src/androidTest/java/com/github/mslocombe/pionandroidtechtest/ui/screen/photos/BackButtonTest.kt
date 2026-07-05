package com.github.mslocombe.pionandroidtechtest.ui.screen.photos

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.github.mslocombe.pionandroidtechtest.R
import org.junit.Rule
import org.junit.Test

class BackButtonTest {

    @get:Rule
    val compose = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun backButtonClickable() {
        var clicked = false

        compose.setContent {
            BackButton {
                clicked = true
            }
        }

        val label = compose.activity.getString(R.string.back)
        compose.onNodeWithContentDescription(label).performClick()
        assert(clicked)
    }
}