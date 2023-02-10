package com.belajarubic.restaurant_jetpackcompose.ui.screen.home

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import com.belajarubic.restaurant_jetpackcompose.R
import com.belajarubic.restaurant_jetpackcompose.ui.theme.RestaurantJetpackComposeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenKtTest {

    @get:Rule
    val composeUiTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeUiTestRule.setContent {
            RestaurantJetpackComposeTheme {
                HomeScreen()
            }
        }
    }

    @Test
    fun check_search_bar() {
        composeUiTestRule.run {
            onNodeWithTag(activity.getString(R.string.search_bar)).assertIsDisplayed()
        }
    }

    @Test
    fun check_icon_favorite() {
        composeUiTestRule.run {
            onNodeWithContentDescription(activity.getString(R.string.favorite)).assertIsEnabled()
        }
    }

    @Test
    fun check_icon_person() {
        composeUiTestRule.run {
            onNodeWithContentDescription(activity.getString(R.string.account)).assertIsEnabled()
        }
    }

    @Test
    fun check_list_restaurant() {
        composeUiTestRule.run {
            onNodeWithTag(activity.getString(R.string.restaurant_list)).assertIsDisplayed()
        }
    }

    @Test
    fun check_restaurant_list_page() {
        composeUiTestRule.run {
            onNodeWithTag(activity.getString(R.string.restaurant_list_page)).assertIsDisplayed()
        }
    }
}