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
 * File: RegisterScreenTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/11/2025
 */

class RegisterScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun RegisterScreen_elementsAreDisplayedAndButtonDisabled(){

        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone()
            ) {
                RegisterScreen()
            }
        }

        composeTestRule.onNodeWithTag(TestTags.ON_BACK_BUTTON_REGISTER).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.HEADER_REGISTER).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.NAME_INPUT_REGISTER).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.EMAIL_INPUT_REGISTER).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.PASSWORD_INPUT_REGISTER).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.CONFIRM_PASSWORD_INPUT_REGISTER).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.REGISTER_BUTTON).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.REGISTER_BUTTON).assertIsNotEnabled()
        composeTestRule.onNodeWithTag(TestTags.LOGIN_LINK).assertIsDisplayed()
    }
}