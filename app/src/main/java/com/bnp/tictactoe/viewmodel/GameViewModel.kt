package com.bnp.tictactoe.viewmodel

import androidx.lifecycle.ViewModel
import com.bnp.tictactoe.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * @Author: 2026-DEV2-029;
 * @DateCreated: Monday, February 02, 2026
 */

@HiltViewModel
class GameViewModel @Inject constructor(private val gameUseCase: GameUseCase) : ViewModel() {

    fun onCellClicked(index: Int) {
        //TODO: Implement this method

    }

    fun onResetClicked() {
        //TODO: Implement this method
    }
}