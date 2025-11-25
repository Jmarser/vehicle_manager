package com.jmarser.vehiclemanager.presentation.components

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme
import org.junit.Rule
import org.junit.Test

/**
 * Project: Vehicle manager
 * File: HeaderAuthTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 25/11/2025
 */

class HeaderAuthTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun headerAuth_withTitle_displaysLogoAndTitle(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val expectedTitle = context.getString(R.string.login_sesion)

        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone()
            ) {
                HeaderAuth(
                    title = R.string.login_sesion,
                    logo = AppImages.logo()
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription("Logo de la app")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(expectedTitle)
            .assertIsDisplayed()
    }

    @Test
    fun headerAuth_withoutTitle_displaysOnlyLogo() {
        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone()
            ) {
                HeaderAuth(
                    title = null,
                    logo = AppImages.logo()
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription("Logo de la app")
            .assertIsDisplayed()

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val expectedTitle = context.getString(R.string.login_sesion)

        composeTestRule
            .onNodeWithText(expectedTitle)
            .assertDoesNotExist()
    }

}