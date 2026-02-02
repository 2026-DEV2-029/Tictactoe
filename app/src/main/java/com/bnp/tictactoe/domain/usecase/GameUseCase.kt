package com.bnp.tictactoe.domain.usecase

import com.bnp.tictactoe.domain.model.GameState
import com.bnp.tictactoe.domain.repository.GameRepository
import javax.inject.Inject


/**
 * @Author: 2026-DEV2-029;
 * @DateCreated: Monday, February 02, 2026
 */
class GameUseCase @Inject constructor(private val gameRepository: GameRepository) {
    fun makeMove(position: Int): GameState {
        return gameRepository.makeMove(position)
    }

    fun resetGame(): GameState {
        return gameRepository.resetGame()
    }
}