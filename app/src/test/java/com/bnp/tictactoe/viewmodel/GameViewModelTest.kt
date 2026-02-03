package com.bnp.tictactoe.viewmodel

import com.bnp.tictactoe.domain.usecase.GameUseCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class GameViewModelTest {

    @Mock
    private lateinit var mockGameUseCase: GameUseCase

    private lateinit var classToTest: GameViewModel

    @Before
    fun setUp() {
        classToTest = GameViewModel(mockGameUseCase)
    }

    @Test
    fun `onCellClicked calls makeMove`() {
        classToTest.onCellClicked(0)
        verify(mockGameUseCase).makeMove(0)
    }

    @Test
    fun `onResetClicked calls resetGame`() {
        classToTest.onResetClicked()
        verify(mockGameUseCase).resetGame()
    }

}