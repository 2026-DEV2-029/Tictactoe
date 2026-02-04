package com.bnp.tictactoe.domain.model

/**
 * @Author: 2026-DEV2-029;
 * @DateCreated: Tuesday, February 02, 2026
 * */

/**
 * Represents the state of the Tic-Tac-Toe game at any given time.
 *
 * @property board The current state of the 3x3 game board. Each element in the list represents a cell,
 * which can be occupied by a [Player] or be null if empty.
 * @property currentPlayer The player whose turn it is to make a move.
 * @property winner The winner of the game, if any. It is null if there is no winner yet.
 * @property isDraw A boolean flag indicating whether the game has ended in a draw.
 * @property winningLine A list of cell indices representing the winning line, if a player has won.
 */
data class GameState(
        val board: List<Player?> = List(9) { null },
        val currentPlayer: Player = Player.X,
        val winner: Player? = null,
        val isDraw: Boolean = false,
        val winningLine: List<Int>? = null
)
