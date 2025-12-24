package com.jmarser.vehiclemanager.presentation.components

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme
import org.junit.Rule
import org.junit.Test

/**
 * Project: Vehicle manager
 * File: PasswordInputFieldTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 25/11/2025
 */

class PasswordInputFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun passwordInputFiled_allowsTyping_andStartsHidden() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val showPassDesc = context.getString(R.string.password_show)
        val semanticText = context.getString(R.string.semantic_password)

        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone(),
            ) {
                val textState = remember { mutableStateOf("") }

                PasswordInputField(
                    value = textState.value,
                    onValueChange = { textState.value = it },
                    semanticText = R.string.semantic_password,
                    iconShow = AppImages.ic_eye_open,
                    iconHide = AppImages.ic_eye_hide,
                    leadingIcon = AppImages.ic_password,
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription(semanticText)
            .performTextInput("123456789")

        composeTestRule
            .onNodeWithContentDescription(showPassDesc)
            .assertIsDisplayed()
    }

    @Test
    fun passwordInputField_toggles_visibility_icon() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val showPassDesc = context.getString(R.string.password_show)
        val hidePassDesc = context.getString(R.string.password_hide)

        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone(),
            ) {
                PasswordInputField(
                    value = "Secret",
                    onValueChange = {},
                    semanticText = R.string.semantic_password,
                    iconShow = AppImages.ic_eye_open,
                    iconHide = AppImages.ic_eye_hide,
                )
            }
        }

        val toggleButton = composeTestRule.onNodeWithContentDescription(showPassDesc)
        toggleButton.performClick()
        composeTestRule
            .onNodeWithContentDescription(hidePassDesc)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithContentDescription(hidePassDesc)
            .performClick()
        composeTestRule
            .onNodeWithContentDescription(showPassDesc)
            .assertIsDisplayed()
    }

    @Test
    fun passwordInputField_showsTooltip_onClick() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val infoDesc = context.getString(R.string.info_password_description)
        val tooltipTitle = context.getString(R.string.title_message_info_password)

        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone(),
            ) {
                PasswordInputField(
                    value = "",
                    onValueChange = {},
                    semanticText = R.string.semantic_password,
                    iconShow = AppImages.ic_eye_open,
                    iconHide = AppImages.ic_eye_hide,
                    leadingIcon = AppImages.ic_password,
                    iconInfo = AppImages.ic_info,
                    iconInfoDescription = R.string.info_password_description,
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription(infoDesc)
            .performClick()

        composeTestRule
            .onNodeWithText(tooltipTitle)
            .assertIsDisplayed()
    }

    @Test
    fun passwordInputField_showsErrorText_whenErrorState() {
        val errorResId = R.string.error_password_invalid
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val errorString = context.getString(errorResId)

        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone(),
            ) {
                PasswordInputField(
                    value = "Password invalido",
                    onValueChange = {},
                    semanticText = R.string.semantic_password,
                    isError = true,
                    textError = errorResId,
                    iconShow = AppImages.ic_eye_open,
                    iconHide = AppImages.ic_eye_hide,
                    leadingIcon = AppImages.ic_password,
                    iconInfo = AppImages.ic_info,
                    iconInfoDescription = R.string.info_password_description,
                )
            }
        }

        composeTestRule
            .onNodeWithText(errorString)
            .assertIsDisplayed()
    }
}
