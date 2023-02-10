package com.belajarubic.restaurant_jetpackcompose

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.belajarubic.restaurant_jetpackcompose.di.Injection
import com.belajarubic.restaurant_jetpackcompose.model.Restaurant
import com.belajarubic.restaurant_jetpackcompose.ui.navigation.Screen
import com.belajarubic.restaurant_jetpackcompose.ui.screen.detail.DetailViewModel
import com.belajarubic.restaurant_jetpackcompose.ui.theme.RestaurantJetpackComposeTheme
import com.belajarubic.restaurant_jetpackcompose.utils.DummyData
import com.belajarubic.restaurant_jetpackcompose.utils.assertCurrentRouteName
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RestaurantAppKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController
    private lateinit var restaurant: Restaurant
    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setUp() {
        restaurant = DummyData.restaurant
        detailViewModel = DetailViewModel(Injection.provideRepository())
        composeTestRule.setContent {
            RestaurantJetpackComposeTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                RestaurantApp(
                    navController = navController,
                )
            }
        }
    }

    @After
    fun tearDown() {
        detailViewModel.removeRestaurantFromFavorite(restaurant.id)
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Splash.route)
    }

    @Test
    fun navHost_verifyOpenToHomePage() {
        composeTestRule.run {
            onNodeWithTag(activity.getString(R.string.splash_page)).assertExists()
            waitUntil(4000L) {
                onAllNodesWithTag(activity.getString(R.string.restaurant_list_page)).fetchSemanticsNodes().size == 1
            }
            onNodeWithTag(activity.getString(R.string.restaurant_list_page)).assertIsDisplayed()
        }
    }

    @Test
    fun navHost_verifyOpenToAccountPage() {
        composeTestRule.run {
            onNodeWithTag(activity.getString(R.string.splash_page)).assertExists()
            waitUntil(4000L) {
                onAllNodesWithTag(activity.getString(R.string.restaurant_list_page)).fetchSemanticsNodes().size == 1
            }
            onNodeWithContentDescription(activity.getString(R.string.account)).run {
                assertIsDisplayed()
                assertIsEnabled()
                performClick()
            }
            onNodeWithTag(activity.getString(R.string.about_me_page)).assertIsDisplayed()
        }
    }

    @Test
    fun navHost_verifyOpenToFavoritePage_isEmptyList() {
        composeTestRule.run {
            onNodeWithTag(activity.getString(R.string.splash_page)).assertExists()
            waitUntil(4000L) {
                onAllNodesWithTag(activity.getString(R.string.restaurant_list_page)).fetchSemanticsNodes().size == 1
            }
            onNodeWithContentDescription(activity.getString(R.string.favorite)).run {
                assertIsDisplayed()
                assertIsEnabled()
                performClick()
            }
            onNodeWithTag(activity.getString(R.string.favorite_restaurant_page)).assertIsDisplayed()
            onNodeWithText(activity.getString(R.string.no_restaurant_item)).run {
                assertIsDisplayed()
            }
        }
    }

    @Test
    fun navHost_verifyOpenToDetailPage_inItemIndexTwo() {
        composeTestRule.run {
            onNodeWithTag(activity.getString(R.string.splash_page)).assertExists()
            waitUntil(4000L) {
                onAllNodesWithTag(activity.getString(R.string.restaurant_list_page)).fetchSemanticsNodes().size == 1
            }
            onNodeWithTag(activity.getString(R.string.restaurant_list)).run {
                onChildAt(1).performClick()
            }
            waitUntil {
                onAllNodesWithTag(activity.getString(R.string.restaurant_detail_page)).fetchSemanticsNodes().size == 1
            }
            onNodeWithText(restaurant.name).assertExists()
            onNodeWithText(restaurant.city).assertExists()
            onNodeWithText(restaurant.rating.toString()).assertExists()
            onNodeWithText(restaurant.description).assertExists()
        }
    }

    @Test
    fun navHost_verifyOpenToDetailPage_saveToFavorite() {
        composeTestRule.run {
            onNodeWithTag(activity.getString(R.string.splash_page)).assertExists()
            waitUntil(4000L) {
                onAllNodesWithTag(activity.getString(R.string.restaurant_list_page)).fetchSemanticsNodes().size == 1
            }
            // to restaurant detail page
            onNodeWithTag(activity.getString(R.string.restaurant_list)).run {
                onChildAt(1).performClick()
            }
            onNodeWithTag(activity.getString(R.string.restaurant_detail_page)).assertIsDisplayed()
            onNodeWithContentDescription(activity.getString(R.string.favorite)).run {
                assertIsDisplayed()
                assertIsEnabled()
                performClick()
            }
            // to home page
            onNodeWithContentDescription(activity.getString(R.string.back_button)).run {
                assertIsDisplayed()
                assertIsEnabled()
                performClick()
            }
            // to favorite page
            onNodeWithContentDescription(activity.getString(R.string.favorite)).run {
                assertIsDisplayed()
                assertIsEnabled()
                performClick()
            }
            waitUntil(4000L) {
                onAllNodesWithTag(activity.getString(R.string.favorite_restaurant_page)).fetchSemanticsNodes().size == 1
            }
            onNodeWithTag(activity.getString(R.string.favorite_restaurant_page)).assertIsDisplayed()
            onAllNodes(hasTestTag(activity.getString(R.string.restaurant_list))).assertCountEquals(1)
        }
    }
}