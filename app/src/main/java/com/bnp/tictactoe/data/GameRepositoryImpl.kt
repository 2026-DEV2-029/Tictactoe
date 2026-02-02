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
        val newBoard = state.board.toMutableList()
            .also {
                it[position] = state.currentPlayer
            }

        return updateState(newBoard)
    }

    private fun updateState(newBoard: List<Player?>): GameState {
        state = state.copy(
                board = newBoard,
                currentPlayer = if (state.currentPlayer == Player.X) Player.O else Player.X,
        )
        return state
    }

    override fun resetGame(): GameState {
        TODO("Not yet implemented")
    }

}