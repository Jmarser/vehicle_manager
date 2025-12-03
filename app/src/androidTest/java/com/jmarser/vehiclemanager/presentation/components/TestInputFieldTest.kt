package com.jmarser.vehiclemanager.presentation.components

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme
import org.junit.Rule
import org.junit.Test

/**
 * Project: Vehicle manager
 * File: TestInputFieldTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 25/11/2025
 */

class TestInputFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun textInputField_displaysCorrectSemanticDescription() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val semanticText = context.getText(R.string.semantic_email)

        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone(),
            ) {
                TextInputField(
                    value = "",
                    onValueChange = {},
                    semanticText = R.string.semantic_email,
                )
            }
        }

        composeTestRule
            .onNodeWithContentDescription(semanticText as String)
            .assertIsDisplayed()
    }

    @Test
    fun textInputField_allowsTyping() {
        val testText = "test@email.com"
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val semanticText = context.getText(R.string.semantic_email)

        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone(),
            ) {
                val textState = remember { mutableStateOf("") }

                TextInputField(
                    value = textState.value,
                    onValueChange = { textState.value = it },
                    semanticText = R.string.semantic_email,
                )
            }
        }

        val inputNode = composeTestRule.onNodeWithContentDescription(semanticText as String)

        inputNode.performTextInput(testText)

        inputNode.assertTextContains(testText)
    }

    @Test
    fun textInputField_showsErrorText_whenErrorState() {
        val errorResId = R.string.error_email_invalid
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val errorString = context.getString(errorResId)

        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone(),
            ) {
                TextInputField(
                    value = "Texto inválido",
                    onValueChange = {},
                    semanticText = R.string.semantic_email,
                    isError = true,
                    textError = errorResId,
                )
            }
        }

        composeTestRule
            .onNodeWithText(errorString)
            .assertIsDisplayed()
    }
}
