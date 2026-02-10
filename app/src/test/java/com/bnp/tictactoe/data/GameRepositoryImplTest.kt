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
    fun `reset game to reset board for new game`() {
        classToTest.makeMove(0)
        val resetState = classToTest.resetGame()

        assertTrue(resetState.board.all { it == null })
        assertNull(resetState.winner)
        assertFalse(resetState.isDraw)
    }

}