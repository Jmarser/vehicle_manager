package com.jmarser.vehiclemanager.presentation.auth.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.core.utils.TestTags
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme
import org.junit.Rule
import org.junit.Test

/**
 * Project: Vehicle manager
 * File: ForgotPasswordScreenTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/11/2025
 */

class ForgotPasswordScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun forgotPasswordScreen_elementsAreDisplayedAndButtonDisabled(){

        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone()
            ) {
                ForgotPasswordScreen()
            }
        }

        composeTestRule.onNodeWithTag(TestTags.ON_BACK_BUTTON_FORGOT).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.HEADER_FORGOT).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.EMAIL_INPUT_FORGOT).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.MESSAGE_INFO_FORGOT).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.FORGOT_BUTTON).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.FORGOT_BUTTON).assertIsNotEnabled()
    }
}