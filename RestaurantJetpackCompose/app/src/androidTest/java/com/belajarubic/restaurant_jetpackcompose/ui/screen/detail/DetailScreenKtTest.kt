package com.belajarubic.restaurant_jetpackcompose.ui.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.belajarubic.restaurant_jetpackcompose.R
import com.belajarubic.restaurant_jetpackcompose.model.Restaurant
import com.belajarubic.restaurant_jetpackcompose.ui.theme.RestaurantJetpackComposeTheme
import com.belajarubic.restaurant_jetpackcompose.utils.DummyData
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailScreenKtTest {

    @get:Rule
    val composeUiTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController
    private lateinit var restaurant: Restaurant

    @Before
    fun setUp() {
        restaurant = DummyData.restaurant
        composeUiTestRule.setContent {
            RestaurantJetpackComposeTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                DetailScreen(
                    id = restaurant.id
                )
            }
        }
    }

    @Test
    fun check_detail_content() {
        composeUiTestRule.run {
            onNodeWithText(restaurant.name).assertExists()
            onNodeWithText(restaurant.city).assertExists()
            onNodeWithText(restaurant.rating.toString()).assertExists()
            onNodeWithText(restaurant.description).assertExists()
        }
    }

    @Test
    fun check_icon_favorite() {
        composeUiTestRule.run {
            onNodeWithContentDescription(activity.getString(R.string.favorite)).run {
                assertIsDisplayed()
                assertIsEnabled()
            }
        }
    }

    @Test
    fun check_detail_page() {
        composeUiTestRule.run {
            onNodeWithTag(activity.getString(R.string.restaurant_detail_page)).assertIsDisplayed()
        }
    }
}