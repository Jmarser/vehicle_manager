package com.jmarser.vehiclemanager.presentation.auth.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
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
 * File: SplashScreenTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 27/11/2025
 */

class SplashScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockNavigateToLogin = mockk<() -> Unit>()
    private val mockNavigateToHome = mockk<() -> Unit>()

    @Before
    fun setup() {
        every { mockNavigateToLogin.invoke() } returns Unit
        every { mockNavigateToHome.invoke() } returns Unit
    }

    private fun setSplashScreenContent(){
        composeTestRule.mainClock.autoAdvance = false
        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone()
            ) {
                SplashScreen(
                    navigateToLogin = mockNavigateToLogin,
                    navigateToHome = mockNavigateToHome
                )
            }
        }
    }

    @Test
    fun splashScreen_elements_are_displayed_and_button_disabled(){

        setSplashScreenContent()

        composeTestRule.onNodeWithTag(TestTags.NAME_APP_SPLASH).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.LOGO_APP_SPLASH).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.SLOGAN_APP_SPLASH).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.DEVELOPER_NAME_SPLASH).assertIsDisplayed()

        verify(exactly = 0) { mockNavigateToLogin.invoke() }
        verify(exactly = 0) { mockNavigateToHome.invoke() }
    }

    @Test
    fun splashScreen_delay_elapsed_naviagte_to_login(){

        setSplashScreenContent()

        composeTestRule.mainClock.advanceTimeBy(4500L)

        verify(exactly = 1) { mockNavigateToLogin.invoke() }
        verify(exactly = 0) { mockNavigateToHome.invoke() }
    }
}