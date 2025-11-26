package com.jmarser.vehiclemanager.presentation.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * Project: Vehicle manager
 * File: ButtonWithPbTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 26/11/2025
 */

class ButtonWithPbTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun buttonWithPb_defaultState_isClickable(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val labelText = context.getString(R.string.login)
        val descText = context.getString(R.string.semantic_button_login)

        var clickCount by mutableIntStateOf(0)

        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone()
            ) {
                ButtonWithPb(
                    label = R.string.login,
                    semanticDescription = R.string.semantic_button_login,
                    isEnabled = true,
                    displayProgressbar = false,
                    value = "data",
                    onClick = { clickCount++ }
                )
            }
        }

        composeTestRule.onNodeWithText(labelText).assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription(descText).assertIsEnabled()

        composeTestRule.onNodeWithContentDescription(descText).performClick()

        assertEquals(1, clickCount.toLong())
    }

    @Test
    fun buttonWithPb_loadingState_showsProgressBarAndHidesText(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val labelText = context.getString(R.string.login)

        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone()
            ) {
                ButtonWithPb(
                    label = R.string.login,
                    semanticDescription = R.string.semantic_button_login,
                    isEnabled = true,
                    displayProgressbar = true,
                    value = "data",
                    onClick = { }
                )
            }
        }

        composeTestRule.onNodeWithText(labelText).assertDoesNotExist()

        composeTestRule.onNode(hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate)).assertIsDisplayed()
    }

    @Test
    fun buttonWithPb_disabledState_isNotClickable(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val descText = context.getString(R.string.semantic_button_login)

        var clickCount by mutableIntStateOf(0)

        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone()
            ) {
                ButtonWithPb(
                    label = R.string.login,
                    semanticDescription = R.string.semantic_button_login,
                    isEnabled = false,
                    displayProgressbar = false,
                    value = "data",
                    onClick = { clickCount++ }
                )
            }
        }

        composeTestRule.onNodeWithContentDescription(descText).assertIsNotEnabled()
        composeTestRule.onNodeWithContentDescription(descText).performClick()
        assertEquals(0, clickCount.toLong())
    }
}