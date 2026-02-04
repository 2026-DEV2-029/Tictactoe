package com.bnp.tictactoe.ui.view

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bnp.tictactoe.R
import com.bnp.tictactoe.domain.model.GameState
import com.bnp.tictactoe.domain.model.Player

/**
 * @Author: 2026-DEV2-029;
 * @DateCreated: Tuesday, February 03, 2026
 * */

/**
 * The main composable for the game screen.
 *
 * @param state The current state of the game.
 * @param onCellClick A callback that is invoked when a cell is clicked.
 * @param onResetClick A callback that is invoked when the reset button is clicked.
 */
@Composable
fun GameContent(
        state: GameState,
        onCellClick: (Int) -> Unit,
        onResetClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ) {

        val message = when {
            state.winner != null -> stringResource(
                    R.string.winner,
                    state.winner
            )

            state.isDraw -> stringResource(R.string.its_a_draw)
            else -> stringResource(
                    R.string.turn,
                    state.currentPlayer
            )
        }
        GameStatus(message)

        Spacer(modifier = Modifier.height(24.dp))

        GameBoard(
                board = state.board,
                enabled = state.winner == null && !state.isDraw,
                winningLine = state.winningLine,
                onCellClick = onCellClick
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onResetClick) {
            Text(text = "Reset Game")
        }
    }
}

/**
 * A composable that displays the current status of the game.
 *
 * @param message The message to be displayed.
 */
@Composable
private fun GameStatus(message: String) {
    Text(
            text = message,
            style = MaterialTheme.typography.headlineSmall
    )
}

/**
 * A composable that displays the game board.
 *
 * @param board The current state of the board.
 * @param enabled Whether the board is enabled for interaction.
 * @param winningLine The line that represents the winning combination, if any.
 * @param onCellClick A callback that is invoked when a cell is clicked.
 */
@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
@Composable
fun GameBoard(
        board: List<Player?>,
        enabled: Boolean,
        winningLine: List<Int>?,
        onCellClick: (Int) -> Unit
) {
    Box(modifier = Modifier.size(300.dp)) {
        LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize(),
                userScrollEnabled = false
        ) {
            createGrid(
                    board,
                    enabled,
                    onCellClick
            )
        }

        // Draw winning line if there is one
        if (winningLine != null && winningLine.size == 3) {
            WinningLine(winningLine = winningLine)
        }
    }
}

/**
 * A composable that displays a single cell on the game board.
 *
 * @param value The player who has marked the cell, or null if it is empty.
 * @param enabled Whether the cell is enabled for interaction.
 * @param onClick A callback that is invoked when the cell is clicked.
 */
@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
@Composable
fun GameCell(
        value: Player?,
        enabled: Boolean,
        onClick: () -> Unit
) {
    Box(
            modifier = Modifier
                .aspectRatio(1f)
                .border(
                        1.dp,
                        Color.Gray
                )
                .clickable(
                        enabled = enabled,
                        onClick = onClick
                ),
            contentAlignment = Alignment.Center
    ) {
        Text(
                text = value?.name ?: "",
                style = MaterialTheme.typography.displayMedium
        )
    }
}

/**
 * A composable that draws a line to indicate the winning combination.
 *
 * @param winningLine The line that represents the winning combination.
 */
@Composable
private fun WinningLine(winningLine: List<Int>) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val startOffset = getCellCenter(
                winningLine.first(),
                size
        )
        val endOffset = getCellCenter(
                winningLine.last(),
                size
        )

        drawLine(
                color = Color.Red,
                start = startOffset,
                end = endOffset,
                strokeWidth = 12f,
                cap = StrokeCap.Round
        )
    }
}

/**
 * A helper function that creates the grid for the game board.
 *
 * @param board The current state of the board.
 * @param enabled Whether the board is enabled for interaction.
 * @param onCellClick A callback that is invoked when a cell is clicked.
 */
private fun LazyGridScope.createGrid(
        board: List<Player?>,
        enabled: Boolean,
        onCellClick: (Int) -> Unit
) {
    items(
            count = board.size,
            key = { it } // stable key = index
    ) { index ->
        GameCell(
                value = board[index],
                enabled = enabled && board[index] == null,
                onClick = { onCellClick(index) })
    }
}

/**
 * A helper function that calculates the center of a cell on the game board.
 *
 * @param index The index of the cell.
 * @param size The size of the game board.
 * @return The offset of the center of the cell.
 */
private fun getCellCenter(
        index: Int,
        size: Size
): Offset {
    val row = index / 3
    val col = index % 3
    val cellSize = size.width / 3
    return Offset(
            x = (col + 0.5f) * cellSize,
            y = (row + 0.5f) * cellSize
    )
}

@Composable
@Preview(showBackground = true)
private fun GameContentPreview() {
    GameContent(
            state = GameState(),
            onCellClick = {},
            onResetClick = {})
}

@Composable
@Preview(showBackground = true)
private fun GameStatusPreview() {
    GameStatus(message = "Player X's turn")
}

@Composable
@Preview(showBackground = true)
private fun GameBoardPreview() {
    GameBoard(
            board = listOf(
                    Player.X,
                    Player.O,
                    null,
                    Player.O,
                    Player.X,
                    null,
                    null,
                    null,
                    Player.X
            ),
            enabled = true,
            winningLine = null,
            onCellClick = {})
}

@Composable
@Preview(showBackground = true)
private fun GameCellPreview() {
    GameCell(
            value = Player.X,
            enabled = true,
            onClick = {})
}

@Composable
@Preview(showBackground = true)
private fun WinningLinePreview() {
    Box(modifier = Modifier.size(300.dp)) {
        LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize(),
                userScrollEnabled = false
        ) {
            items(9) { index ->
                Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .border(
                                    1.dp,
                                    Color.Gray
                            ),
                        contentAlignment = Alignment.Center
                ) {
                    Text(
                            text = when (index) {
                                0, 1, 2 -> "X"
                                else -> ""
                            },
                            style = MaterialTheme.typography.displayMedium
                    )
                }
            }
        }
        // Horizontal winning line (top row: indices 0, 1, 2)
        WinningLine(
                winningLine = listOf(
                        0,
                        1,
                        2
                )
        )
    }
}
