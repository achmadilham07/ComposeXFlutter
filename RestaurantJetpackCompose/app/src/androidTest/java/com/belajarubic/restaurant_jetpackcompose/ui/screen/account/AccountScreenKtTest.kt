package com.belajarubic.restaurant_jetpackcompose.ui.screen.account

import androidx.activity.ComponentActivity
import androidx.annotation.DrawableRes
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.belajarubic.restaurant_jetpackcompose.R
import com.belajarubic.restaurant_jetpackcompose.model.Account
import com.belajarubic.restaurant_jetpackcompose.ui.theme.RestaurantJetpackComposeTheme
import com.belajarubic.restaurant_jetpackcompose.utils.DummyData
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AccountScreenKtTest {

    @get:Rule
    val composeUiTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    private lateinit var account: Account
    private var imageCategory: Int = 0

    @Before
    fun setUp() {
        account = DummyData.account
        imageCategory = account.imageCategory
        composeUiTestRule.setContent {
            RestaurantJetpackComposeTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                AccountScreen()
            }
        }
    }

    @Test
    fun check_account_content() {
        composeUiTestRule.run {
            onNodeWithText(account.name).assertExists()
            onNodeWithText(account.email).assertExists()
            onNode(hasDrawable(imageCategory))
                .assertIsDisplayed()
        }
    }

    @Test
    fun check_account_page(){
        composeUiTestRule.run {
            onNodeWithTag(activity.getString(R.string.about_me_page)).assertIsDisplayed()
        }
    }

    private fun hasDrawable(@DrawableRes id: Int): SemanticsMatcher =
        SemanticsMatcher.expectValue(DrawableId, id)
}