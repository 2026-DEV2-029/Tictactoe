package com.bnp.tictactoe.ui.view

import androidx.compose.runtime.Composable
import com.bnp.tictactoe.domain.model.GameState
import com.bnp.tictactoe.domain.model.Player


/**
 * @Author: 2026-DEV2-029;
 * @DateCreated: Tuesday, February 03, 2026
 */
@Composable
fun GameContent(
        state: GameState,
        onCellClick: (Int) -> Unit,
        onResetClick: () -> Unit
) {
}

@Composable
private fun GameStatus(message: String) {
}


@Composable
fun GameBoard(
        board: List<Player?>,
        enabled: Boolean,
        winningLine: List<Int>?,
        onCellClick: (Int) -> Unit
) {

}


@Composable
fun GameCell(
        value: Player?,
        enabled: Boolean,
        onClick: () -> Unit
) {

}
