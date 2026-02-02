package com.bnp.tictactoe.data

import com.bnp.tictactoe.domain.model.Player
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * @Author: 2026-DEV2-029;
 * @DateCreated: Monday, February 02, 2026
 */
class GameRepositoryImplTest {

    lateinit var classToTest: GameRepositoryImpl

    @Before
    fun setUp() {
        classToTest = GameRepositoryImpl()
    }

    @Test
    fun `make move updates board with player move`() {
        classToTest.makeMove(0)
        val updatedState = classToTest.makeMove(1)

        assertEquals(
                Player.X,
                updatedState.board[0]
        )
        assertEquals(
                Player.O,
                updatedState.board[1]
        )
    }

    @Test
    fun `make move does not allow move on occupied position`() {
        classToTest.makeMove(0)
        val stateAfterInvalidMove = classToTest.makeMove(0)

        assertEquals(
                Player.X,
                stateAfterInvalidMove.board[0]
        )
        assertNull(stateAfterInvalidMove.board[1])
    }

    @Test
    fun `make move does not allow move after game is won`() {
        val winningMoves = listOf(
                0,
                3,
                1,
                4,
                2
        ) // Player X wins
        winningMoves.forEach { classToTest.makeMove(it) }
        val stateAfterWin = classToTest.makeMove(5)

        assertEquals(
                Player.X,
                stateAfterWin.winner
        )
        assertNull(stateAfterWin.board[5])
    }

    @Test
    fun `make move detects draw condition`() {
        val drawMoves = listOf(
                0,
                1,
                2,
                4,
                3,
                5,
                7,
                6,
                8
        ) // No winner
        drawMoves.forEach { classToTest.makeMove(it) }
        val finalState = classToTest.makeMove(8)

        assertTrue(finalState.isDraw)
        assertNull(finalState.winner)
    }

    @Test
    fun `reset game to reset board for new game`() {
        classToTest.makeMove(0)
        val resetState = classToTest.resetGame()

        assertTrue(resetState.board.all { it == null })
        assertNull(resetState.winner)
        assertFalse(resetState.isDraw)
    }

}