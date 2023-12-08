package com.marti_cv.minevera.shopping.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class ShoppingScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun checkButtonsExistTest() {
        composeTestRule.setContent {
            OptionButtons(onClickDelete = {}, onClickAdd = {}, modifier = Modifier.fillMaxSize())
        }

        composeTestRule.onNodeWithTag("add").assertExists()
    }

    @Test
    fun onShowTrue_thenDialogIsShown() {
        composeTestRule.setContent {
            AddItemDialog(show = true, onDismiss = { }, onItemAdded = {})
        }
        composeTestRule.onNodeWithTag("addItemDialog").assertIsDisplayed()
    }

    @Test
    fun onShowFalse_thenDialogIsShown() {
        composeTestRule.setContent {
            AddItemDialog(show = false, onDismiss = { }, onItemAdded = {})
        }
        composeTestRule.onNodeWithTag("addItemDialog").assertDoesNotExist()
    }

}
