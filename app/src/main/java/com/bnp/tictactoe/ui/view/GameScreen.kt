package com.bnp.tictactoe.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bnp.tictactoe.viewmodel.GameViewModel


/**
 * @Author: 2026-DEV2-029;
 * @DateCreated: Tuesday, February 03, 2026
 */
@Composable
internal fun GameScreen(
        viewModel: GameViewModel = hiltViewModel()
) {
    val state by viewModel.gameState.collectAsStateWithLifecycle()

    GameContent(
            state = state,
            onCellClick = viewModel::onCellClicked,
            onResetClick = viewModel::onResetClicked
    )
}