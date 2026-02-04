package com.bnp.tictactoe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bnp.tictactoe.domain.model.GameState
import com.bnp.tictactoe.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Author: 2026-DEV2-029;
 * @DateCreated: Tuesday, February 02, 2026
 * */

/**
 * The ViewModel for the game screen.
 *
 * @property gameUseCase The use case that handles the business logic of the game.
 */
@HiltViewModel
class GameViewModel @Inject constructor(private val gameUseCase: GameUseCase) : ViewModel() {

    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    /**
     * Called when a cell is clicked.
     *
     * @param index The index of the clicked cell.
     */
    fun onCellClicked(index: Int) {
        viewModelScope.launch {
            _gameState.value = gameUseCase.makeMove(index)
        }

    }

    /**
     * Called when the reset button is clicked.
     */
    fun onResetClicked() {
        viewModelScope.launch {
            _gameState.value = gameUseCase.resetGame()
        }
    }
}