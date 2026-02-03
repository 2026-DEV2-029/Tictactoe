package com.bnp.tictactoe.domain.usecase

import com.bnp.tictactoe.domain.model.GameState
import com.bnp.tictactoe.domain.model.Player
import com.bnp.tictactoe.domain.repository.GameRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GameUseCaseTest {

    @Mock
    private lateinit var fakeGameRepository: GameRepository

    private lateinit var classToTest: GameUseCase

    @Before
    fun setUp() {
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
        val gameState = GameState(
                board = listOf(
                        Player.X,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                ),
                currentPlayer = Player.O,
                winner = null
        )
        whenever(fakeGameRepository.makeMove(0)).thenReturn(gameState)
        val state = classToTest.makeMove(0)
        assertEquals(Player.X, state.board[0])
        assertEquals(Player.O, state.currentPlayer)
    }

    @Test
    fun `make move updates board with player move`() {
        val initialBoard: List<Player?> = List(size = 9) { null }
        val boardAfterFirstMove = initialBoard.toMutableList().apply { this[0] = Player.X }
        val finalBoard = boardAfterFirstMove.toMutableList().apply { this[1] = Player.O }

        whenever(fakeGameRepository.makeMove(0)).thenReturn(GameState(board = boardAfterFirstMove))
        whenever(fakeGameRepository.makeMove(1)).thenReturn(GameState(board = finalBoard))

        classToTest.makeMove(0)
        val updatedState = classToTest.makeMove(1)

        assertEquals(Player.X, updatedState.board[0])
        assertEquals(Player.O, updatedState.board[1])
    }

    @Test
    fun `make move does not allow move on occupied position`() {
        val initialBoard: List<Player?> = List(size = 9) { null }
        val boardAfterFirstMove = initialBoard.toMutableList().apply { this[0] = Player.X }

        whenever(fakeGameRepository.makeMove(0)).thenReturn(GameState(board = boardAfterFirstMove))

        classToTest.makeMove(0)
        val stateAfterInvalidMove = classToTest.makeMove(0)

        assertEquals(Player.X, stateAfterInvalidMove.board[0])
        assertNull(stateAfterInvalidMove.board[1])
    }

    @Test
    fun `make move does not allow move after game is won`() {
        val winningBoard = mutableListOf<Player?>().apply {
            repeat(9) { add(null) }
            this[0] = Player.X
            this[3] = Player.O
            this[1] = Player.X
            this[4] = Player.O
            this[2] = Player.X
        }

        val gameState = GameState(board = winningBoard, winner = Player.X)
        whenever(fakeGameRepository.makeMove(5)).thenReturn(gameState)

        val stateAfterWin = classToTest.makeMove(5)

        assertEquals(Player.X, stateAfterWin.winner)
        assertNull(stateAfterWin.board[5])
    }

    @Test
    fun `make move detects draw condition`() {
        val drawBoard = mutableListOf(
            Player.X, Player.O, Player.X,
            Player.X, Player.O, Player.O,
            Player.O, Player.X, Player.X
        )
        val gameState = GameState(board = drawBoard, isDraw = true)
        whenever(fakeGameRepository.makeMove(8)).thenReturn(gameState)

        val finalState = classToTest.makeMove(8)

        assertTrue(finalState.isDraw)
        assertNull(finalState.winner)
    }

    @Test
    fun `reset game to reset board for new game`() {
        val initialBoard = List(9) { null }
        val gameState = GameState(board = initialBoard)
        whenever(fakeGameRepository.resetGame()).thenReturn(gameState)

        val resetState = classToTest.resetGame()

        assertTrue(resetState.board.all { it == null })
        assertNull(resetState.winner)
        assertFalse(resetState.isDraw)
    }
}
