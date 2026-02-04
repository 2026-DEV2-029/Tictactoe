package com.bnp.tictactoe.data

import com.bnp.tictactoe.domain.model.GameState
import com.bnp.tictactoe.domain.model.Player
import com.bnp.tictactoe.domain.repository.GameRepository


/**
 * @Author: 2026-DEV2-029;
 * @DateCreated: Monday, February 02, 2026
 */
class GameRepositoryImpl : GameRepository {

    private var state = GameState()
    /**
     * Makes a move on the board at the given position.
     *
     * @param position The position on the board where the move is to be made.
     * @return The updated game state after the move.
     */
    override fun makeMove(position: Int): GameState {
        if (state.board[position] != null || state.winner != null) {
            return state
        }
        val newBoard = state.board.toMutableList()
            .also {
                it[position] = state.currentPlayer
            }

        return updateState(newBoard)
    }

    /**
     * Resets the game to its initial state.
     *
     * @return The initial game state.
     */
    override fun resetGame(): GameState {
        state = GameState()
        return state
    }

    /**
     * Updates the game state after a move has been made.
     *
     * @param newBoard The new state of the board after the move.
     * @return The updated game state.
     */
    private fun updateState(newBoard: List<Player?>): GameState {
        val (winner, winningLine) = checkWinner(newBoard)
        val isDraw = winner == null && newBoard.all { it != null }
        state = state.copy(
                board = newBoard,
                currentPlayer = if (state.currentPlayer == Player.X) Player.O else Player.X,
                winner = winner,
                isDraw = isDraw,
                winningLine = winningLine
        )
        return state
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