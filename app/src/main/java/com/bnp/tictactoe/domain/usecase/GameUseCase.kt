package com.bnp.tictactoe.domain.usecase

import com.bnp.tictactoe.domain.model.GameState
import com.bnp.tictactoe.domain.model.Player
import com.bnp.tictactoe.domain.repository.GameRepository
import javax.inject.Inject

/**
 * @Author: 2026-DEV2-029;
 * @DateCreated: Tuesday, February 02, 2026
 * */

/**
 * A use case that handles the business logic of the game.
 *
 * @property gameRepository The repository that provides the game data.
 */
class GameUseCase @Inject constructor(private val gameRepository: GameRepository) {
    /**
     * Makes a move on the board at the given position.
     *
     * @param position The position on the board where the move is to be made.
     * @return The updated game state after the move.
     */
    fun makeMove(position: Int): GameState {
        val currentState = gameRepository.makeMove(position)
        val (winner, winningLine) = checkWinner(currentState.board)
        val isDraw = winner == null && currentState.board.all { it != null }
        return currentState.copy(
            winner = winner,
            isDraw = isDraw,
            winningLine = winningLine
        )
    }

    /**
     * Resets the game to its initial state.
     *
     * @return The initial game state.
     */
    fun resetGame(): GameState {
        return gameRepository.resetGame()
    }

    /**
     * Checks for a winner on the board.
     *
     * @param board The current state of the board.
     * @return A pair containing the winner and the winning line, if any.
     */
    private fun checkWinner(board: List<Player?>): Pair<Player?, List<Int>?> {
        val winningPositions = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8), //horizontal
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),  //Vertical
            listOf(0, 4, 8), listOf(2, 4, 6) //Diagonal
        )
        return winningPositions.firstNotNullOfOrNull { positions ->
            val (a, b, c) = positions
            if (board[a] != null && board[a] == board[b] && board[a] == board[c]) {
                Pair(board[a], positions)
            } else {
                null
            }
        } ?: Pair(null, null)
    }
}