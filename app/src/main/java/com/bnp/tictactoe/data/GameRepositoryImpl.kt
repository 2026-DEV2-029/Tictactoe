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

    private fun updateState(newBoard: List<Player?>): GameState {
        val (winner, winningLine) = checkWinner(newBoard)
        state = state.copy(
                board = newBoard,
                currentPlayer = if (state.currentPlayer == Player.X) Player.O else Player.X,
                winner = winner,
                winningLine = winningLine
        )
        return state
    }

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

    override fun resetGame(): GameState {
        TODO("Not yet implemented")
    }

}