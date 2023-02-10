package com.belajarubic.restaurant_jetpackcompose.ui.composable

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.belajarubic.restaurant_jetpackcompose.model.Restaurant
import com.belajarubic.restaurant_jetpackcompose.ui.theme.RestaurantJetpackComposeTheme
import com.belajarubic.restaurant_jetpackcompose.utils.DummyData
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RestaurantItemKtTest {

    @get:Rule
    val composeUiTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var restaurant: Restaurant

    @Before
    fun setUp() {
        restaurant = DummyData.restaurant
        composeUiTestRule.setContent {
            RestaurantJetpackComposeTheme {
                RestaurantItem(
                    restaurant = restaurant
                )
            }
        }
    }

    @Test
    fun check_circular_indicator() {
        composeUiTestRule.run {
            onNodeWithContentDescription(
                "${restaurant.name} item"
            ).assertIsDisplayed()
        }
    }
}