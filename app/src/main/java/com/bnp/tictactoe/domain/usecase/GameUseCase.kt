package com.bnp.tictactoe.domain.usecase

import com.bnp.tictactoe.domain.model.GameState
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
        return gameRepository.makeMove(position)
    }

    /**
     * Resets the game to its initial state.
     *
     * @return The initial game state.
     */
    fun resetGame(): GameState {
        return gameRepository.resetGame()
    }
}