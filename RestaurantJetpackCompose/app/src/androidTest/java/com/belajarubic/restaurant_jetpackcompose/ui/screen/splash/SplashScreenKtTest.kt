package com.belajarubic.restaurant_jetpackcompose.ui.screen.splash

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.belajarubic.restaurant_jetpackcompose.R
import com.belajarubic.restaurant_jetpackcompose.ui.theme.RestaurantJetpackComposeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SplashScreenKtTest {

    @get:Rule
    val composeUiTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeUiTestRule.setContent {
            RestaurantJetpackComposeTheme {
                SplashScreen(
                    navigateToHome = {}
                )
            }
        }
    }


    @Test
    fun checking_a_text_on_page() {
        composeUiTestRule.onNodeWithText(
            composeUiTestRule.activity.getString(
                R.string.splash_text,
                0
            )
        ).assertExists()
    }

    @Test
    fun checking_a_circular_progress_on_page() {
        composeUiTestRule.onNodeWithTag(
            composeUiTestRule.activity.getString(R.string.splash_loading)
        ).assertIsDisplayed()
    }
}
