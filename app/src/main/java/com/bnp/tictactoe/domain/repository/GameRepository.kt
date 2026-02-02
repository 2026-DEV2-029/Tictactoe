package com.bnp.tictactoe.domain.repository

import com.bnp.tictactoe.domain.model.GameState


/**
 * @Author: 2026-DEV2-029;
 * @DateCreated: Monday, February 02, 2026
 */
interface GameRepository {
    fun makeMove(position: Int): GameState
    fun resetGame(): GameState
}