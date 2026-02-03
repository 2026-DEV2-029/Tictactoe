package com.bnp.tictactoe.ui.view

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.bnp.tictactoe.domain.model.GameState
import com.bnp.tictactoe.domain.model.Player
import org.junit.Rule
import org.junit.Test

/**
 * @Author: 2026-DEV2-029;
 * @DateCreated: Tuesday, February 03, 2026
 */
class GameContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun gameContent_displaysInitialState() {
        val state = GameState()
        composeTestRule.setContent {
            GameContent(
                    state = state,
                    onCellClick = {},
                    onResetClick = {})
        }

        composeTestRule.onNodeWithText("Turn: X")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("Reset Game")
            .assertIsDisplayed()
    }

    @Test
    fun gameContent_handlesWin() {
        val state = GameState(winner = Player.X)
        composeTestRule.setContent {
            GameContent(
                    state = state,
                    onCellClick = {},
                    onResetClick = {})
        }

        composeTestRule.onNodeWithText("Winner: X")
            .assertIsDisplayed()
    }

    @Test
    fun gameContent_handlesDraw() {
        val state = GameState(isDraw = true)
        composeTestRule.setContent {
            GameContent(
                    state = state,
                    onCellClick = {},
                    onResetClick = {})
        }

        composeTestRule.onNodeWithText("It's a Draw!")
            .assertIsDisplayed()
    }

    @Test
    fun gameBoard_rendersBoard() {
        val board = List(9) { if (it % 2 == 0) Player.X else Player.O }
        composeTestRule.setContent {
            GameBoard(
                    board = board,
                    enabled = true,
                    winningLine = null,
                    onCellClick = {})
        }
        composeTestRule.onAllNodesWithText("O")
            .fetchSemanticsNodes()
            .isNotEmpty()
        composeTestRule.onAllNodesWithText("X")
            .fetchSemanticsNodes()
            .isNotEmpty()

        composeTestRule.onAllNodesWithText("X")[0].assertIsDisplayed()
        composeTestRule.onAllNodesWithText("O")[0].assertIsDisplayed()
    }

    @Test
    fun gameCell_clickableWhenEnabled() {
        var clicked = false
        composeTestRule.setContent {
            GameCell(
                    value = null,
                    enabled = true,
                    onClick = { clicked = true })
        }

        composeTestRule.onNodeWithText("")
            .performClick()
        assert(clicked)
    }

    @Test
    fun gameCell_notClickableWhenDisabled() {
        var clicked = false
        composeTestRule.setContent {
            GameCell(
                    value = Player.X,
                    enabled = false,
                    onClick = { clicked = true })
        }

        composeTestRule.onNodeWithText("X")
            .performClick()
        assert(!clicked)
    }

}