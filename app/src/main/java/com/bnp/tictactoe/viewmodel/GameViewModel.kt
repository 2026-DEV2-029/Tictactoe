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
 * @DateCreated: Monday, February 02, 2026
 */

@HiltViewModel
class GameViewModel @Inject constructor(private val gameUseCase: GameUseCase) : ViewModel() {

    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()
    fun onCellClicked(index: Int) {
        viewModelScope.launch {
            _gameState.value = gameUseCase.makeMove(index)
        }

    }

    fun onResetClicked() {
        viewModelScope.launch {
            _gameState.value = gameUseCase.resetGame()
        }
    }
}