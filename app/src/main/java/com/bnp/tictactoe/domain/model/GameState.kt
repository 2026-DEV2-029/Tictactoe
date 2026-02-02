package com.bnp.tictactoe.domain.model


/**
 * @Author: 2026-DEV2-029;
 * @DateCreated: Monday, February 02, 2026
 */
data class GameState(
        val board: List<Player?> = List(9) { null },
        val currentPlayer: Player = Player.X,
        val winner: Player? = null,
        val isDraw: Boolean = false,
        val winningLine: List<Int>? = null
)
