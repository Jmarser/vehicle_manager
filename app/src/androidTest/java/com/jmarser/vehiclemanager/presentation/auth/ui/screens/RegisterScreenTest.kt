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
 * File: RegisterScreenTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/11/2025
 */

class RegisterScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockNavigateToHome = mockk<() -> Unit>()
    private val mockNavigateToLogin = mockk<() -> Unit>()

    @Before
    fun setUp() {
        every { mockNavigateToLogin.invoke() } returns Unit
        every { mockNavigateToHome.invoke() } returns Unit

        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone(),
            ) {
                RegisterScreen(
                    navigateToLogin = mockNavigateToLogin,
                    navigateToHome = mockNavigateToHome,
                )
            }
        }
    }

    @Test
    fun RegisterScreen_elementsAreDisplayedAndButtonDisabled() {
        composeTestRule.onNodeWithTag(TestTags.ON_BACK_BUTTON_REGISTER).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.HEADER_REGISTER).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.NAME_INPUT_REGISTER).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.EMAIL_INPUT_REGISTER).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.PASSWORD_INPUT_REGISTER).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.CONFIRM_PASSWORD_INPUT_REGISTER).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.REGISTER_BUTTON).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.REGISTER_BUTTON).assertIsNotEnabled()
        composeTestRule.onNodeWithTag(TestTags.LOGIN_LINK).assertIsDisplayed()

        verify(exactly = 0) { mockNavigateToLogin.invoke() }
        verify(exactly = 0) { mockNavigateToHome.invoke() }
    }

    @Test
    fun registerScreen_login_lick_click_navigate_to_login_screen() {
        composeTestRule.onNodeWithTag(TestTags.CLICKABLE_TEXT_COMPONENT).performClick()
        verify(exactly = 1) { mockNavigateToLogin.invoke() }
        verify(exactly = 0) { mockNavigateToHome.invoke() }
    }

    @Test
    fun registerScreen_on_back_lick_click_navigate_to_login_screen() {
        composeTestRule.onNodeWithTag(TestTags.ON_BACK_BUTTON_REGISTER).performClick()
        verify(exactly = 1) { mockNavigateToLogin.invoke() }
        verify(exactly = 0) { mockNavigateToHome.invoke() }
    }
}
