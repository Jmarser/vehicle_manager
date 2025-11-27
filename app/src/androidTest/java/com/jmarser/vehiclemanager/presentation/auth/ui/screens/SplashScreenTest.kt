package com.jmarser.vehiclemanager.presentation.auth.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.core.utils.TestTags
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme
import org.junit.Rule
import org.junit.Test

/**
 * Project: Vehicle manager
 * File: SplashScreenTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/11/2025
 */

class SplashScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun splashScreen_elements_are_displayed_and_button_disabled(){

        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone()
            ) {
                SplashScreen()
            }
        }

        composeTestRule.onNodeWithTag(TestTags.NAME_APP_SPLASH).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.LOGO_APP_SPLASH).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.SLOGAN_APP_SPLASH).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.DEVELOPER_NAME_SPLASH).assertIsDisplayed()
    }
}