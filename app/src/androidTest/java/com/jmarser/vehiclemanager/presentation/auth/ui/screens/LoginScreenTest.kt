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
 * File: LoginScreenTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 26/11/2025
 */

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockNavigateToRegister = mockk<() -> Unit>()
    private val mockNavigateToForgotPassword = mockk<() -> Unit>()
    private val mockNaviagteToHome = mockk<() -> Unit>()

    @Before
    fun setUp(){
        every { mockNavigateToRegister.invoke() } returns Unit
        every { mockNavigateToForgotPassword.invoke() } returns Unit
        every { mockNaviagteToHome.invoke() } returns Unit

        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone()
            ) {
                LoginScreen(
                    navigateToRegister = mockNavigateToRegister,
                    navigateToForgotPassword = mockNavigateToForgotPassword,
                    navigateToHome = mockNaviagteToHome
                )
            }
        }
    }

    @Test
    fun loginScreen_initialState_elementsAreDisplayedAndButtonDisabled(){

        composeTestRule.onNodeWithTag(TestTags.HEADER_LOGIN).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.EMAIL_INPUT_LOGIN).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.PASSWORD_INPUT_LOGIN).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.FORGOT_PASSWORD_BUTTON).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.LOGIN_BUTTON).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.LOGIN_BUTTON).assertIsNotEnabled()
        composeTestRule.onNodeWithTag(TestTags.REGISTER_LINK).assertIsDisplayed()

        verify(exactly = 0) { mockNavigateToForgotPassword.invoke() }
        verify(exactly = 0) { mockNavigateToRegister.invoke() }
        verify(exactly = 0) { mockNaviagteToHome.invoke() }
    }

    @Test
    fun loginScreen_forgot_password_click_navigate_to_forgot_password(){
        composeTestRule.onNodeWithTag(TestTags.FORGOT_PASSWORD_BUTTON).performClick()

        verify(exactly = 1) { mockNavigateToForgotPassword.invoke() }
        verify(exactly = 0) { mockNavigateToRegister.invoke() }
        verify(exactly = 0) { mockNaviagteToHome.invoke() }
    }

    @Test
    fun loginScreen_register_link_click_navigate_to_register(){
        composeTestRule.onNodeWithTag(TestTags.CLICKABLE_TEXT_COMPONENT).performClick()

        verify(exactly = 0) { mockNavigateToForgotPassword.invoke() }
        verify(exactly = 1) { mockNavigateToRegister.invoke() }
        verify(exactly = 0) { mockNaviagteToHome.invoke() }
    }

}