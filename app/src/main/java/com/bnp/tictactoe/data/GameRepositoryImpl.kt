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
        state = state.copy(
            board = newBoard,
            currentPlayer = if (state.currentPlayer == Player.X) Player.O else Player.X
        )
        return state
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

}