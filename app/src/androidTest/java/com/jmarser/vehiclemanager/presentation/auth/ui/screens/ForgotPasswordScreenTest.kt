package com.jmarser.vehiclemanager.presentation.auth.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.core.utils.TestTags
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
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

    private val mockNavigateToLogin = mockk<() -> Unit>()

    @Before
    fun setUp() {
        every { mockNavigateToLogin.invoke() } returns Unit
        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone(),
            ) {
                ForgotPasswordScreen(
                    navigateToLogin = mockNavigateToLogin,
                )
            }
        }
    }

    @Test
    fun forgotPasswordScreen_elementsAreDisplayedAndButtonDisabled() {
        composeTestRule.onNodeWithTag(TestTags.ON_BACK_BUTTON_FORGOT).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.HEADER_FORGOT).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.EMAIL_INPUT_FORGOT).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.MESSAGE_INFO_FORGOT).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.FORGOT_BUTTON).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.FORGOT_BUTTON).assertIsNotEnabled()

        verify(exactly = 0) { mockNavigateToLogin.invoke() }
    }

    @Test
    fun forgotPasswordScreen_on_back_button_click_navigate_to_login() {
        composeTestRule.onNodeWithTag(TestTags.ON_BACK_BUTTON_FORGOT).performClick()
        verify(exactly = 1) { mockNavigateToLogin.invoke() }
    }
}
