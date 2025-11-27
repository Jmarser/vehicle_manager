package com.jmarser.vehiclemanager.presentation.auth.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.platform.app.InstrumentationRegistry
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.core.utils.TestTags
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme
import org.junit.Rule
import org.junit.Test

/**
 * Project: Vehicle manager
 * File: LoginScreenTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 26/11/2025
 */

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun loginScreen_initialState_elementsAreDisplayedAndButtonDisabled(){

        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone()
            ) {
                LoginScreen()
            }
        }

        composeTestRule.onNodeWithTag(TestTags.HEADER_LOGIN).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.EMAIL_INPUT_LOGIN).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.PASSWORD_INPUT_LOGIN).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.FORGOT_PASSWORD_BUTTON).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.LOGIN_BUTTON).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.LOGIN_BUTTON).assertIsNotEnabled()
        composeTestRule.onNodeWithTag(TestTags.REGISTER_LINK).assertIsDisplayed()
    }

}