package com.jmarser.vehiclemanager.presentation.auth.ui.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties.Role
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.test.platform.app.InstrumentationRegistry
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * Project: Vehicle manager
 * File: MyClickableTextTest
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 26/11/2025
 */

class MyClickableTextTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myClickableText_displaysCombinedText() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        val normalText = context.getString(R.string.dont_have_account)
        val clickableText = context.getString(R.string.register_now)

        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone()
            ) {
                MyClickableText(
                    textNormal = R.string.dont_have_account,
                    textClickable = R.string.register_now,
                    textDescription = R.string.clickable_text_description_login,
                    onClick = {}
                )
            }
        }

        composeTestRule
            .onNodeWithText(normalText)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(clickableText)
            .assertIsDisplayed()
    }

    @Test
    fun myClickableText_performClickAndHasButtonRole(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        val descriptionText = context.getString(R.string.clickable_text_description_login)

        var clickCount by mutableIntStateOf(0)

        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone()
            ) {
                MyClickableText(
                    textNormal = R.string.dont_have_account,
                    textClickable = R.string.register_now,
                    textDescription = R.string.clickable_text_description_login,
                    onClick = { clickCount++}
                )
            }
        }

        val node = composeTestRule.onNode(hasContentDescription(descriptionText))

        node.assert(SemanticsMatcher.expectValue(Role, androidx.compose.ui.semantics.Role.Button))

        node.performClick()

        assertEquals(1, clickCount.toLong())

    }

    @Test
    fun myClickableText_performsSemanticsClickAction(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val descriptionText = context.getString(R.string.clickable_text_description_login)
        var clickCount by mutableIntStateOf(0)

        composeTestRule.setContent {
            MyAppTheme(
                windowSizeClass = getSizeForPhone()
            ) {
                MyClickableText(
                    textNormal = R.string.dont_have_account,
                    textClickable = R.string.register_now,
                    textDescription = R.string.clickable_text_description_login,
                    onClick = { clickCount += 2}
                )
            }
        }

        val node = composeTestRule.onNode(hasContentDescription(descriptionText))
        node.performSemanticsAction(SemanticsActions.OnClick)
        assertEquals(2, clickCount.toLong())
    }

}