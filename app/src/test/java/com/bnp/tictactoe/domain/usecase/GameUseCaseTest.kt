package com.bnp.tictactoe.domain.usecase

import com.bnp.tictactoe.domain.model.Player
import com.bnp.tictactoe.domain.repository.GameRepository
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import kotlin.test.assertEquals
import kotlin.test.assertNull

/**
 * @Author: 2026-DEV2-029;
 * @DateCreated: Monday, February 02, 2026
 */
class GameUseCaseTest {

    private lateinit var fakeGameRepository: GameRepository
    private lateinit var classToTest: GameUseCase

    @Before
    fun setUp() {
        fakeGameRepository = mock()
        classToTest = GameUseCase(fakeGameRepository)
    }

    @Test
    fun `makeMove from GameRepository is called`() {
        classToTest.makeMove(0)
        verify(mock = fakeGameRepository).makeMove(0)
    }

    @Test
    fun `resetGame from GameRepository is called`() {
        classToTest.resetGame()
        verify(mock = fakeGameRepository).resetGame()
    }

    @Test
    fun `first move should place X at selected position`() {
        val state = classToTest.makeMove(0)

        assertEquals(Player.X, state.board[0])
        assertEquals(Player.O, state.currentPlayer)
        assertNull(state.winner)
    }
}