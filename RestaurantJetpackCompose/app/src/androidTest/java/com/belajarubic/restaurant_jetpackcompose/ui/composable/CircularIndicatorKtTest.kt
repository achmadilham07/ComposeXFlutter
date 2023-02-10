package com.belajarubic.restaurant_jetpackcompose.ui.composable

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.belajarubic.restaurant_jetpackcompose.R
import com.belajarubic.restaurant_jetpackcompose.ui.theme.RestaurantJetpackComposeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CircularIndicatorKtTest {

    @get:Rule
    val composeUiTestRule = createAndroidComposeRule<ComponentActivity>()


    @Before
    fun setUp() {
        composeUiTestRule.setContent {
            RestaurantJetpackComposeTheme {
                CircularIndicator()
            }
        }
    }

    @Test
    fun check_circular_indicator(){
        composeUiTestRule.run {
            onNodeWithTag(
                activity.resources.getString(R.string.loading)
            ).assertExists()
        }
    }
}