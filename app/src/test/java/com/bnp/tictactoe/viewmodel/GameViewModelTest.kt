package com.bnp.tictactoe.viewmodel

import com.bnp.tictactoe.domain.model.GameState
import com.bnp.tictactoe.domain.model.Player
import com.bnp.tictactoe.domain.usecase.GameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GameViewModelTest {

    @Mock
    private lateinit var fakeGameUseCase: GameUseCase

    private lateinit var classToTest: GameViewModel
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        classToTest = GameViewModel(fakeGameUseCase)
    }

    @Test
    fun `onCellClicked calls makeMove`() = runTest {
        val newGameState = GameState(
                board = listOf(
                        Player.X,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                )
        )
        whenever(fakeGameUseCase.makeMove(0)).thenReturn(newGameState)

        classToTest.onCellClicked(0)
        testDispatcher.scheduler.advanceUntilIdle()

        //makeMove is called
        verify(fakeGameUseCase).makeMove(0)
        //position is updated on the board
        assertEquals(newGameState, classToTest.gameState.value)
    }

    @Test
    fun `onResetClicked calls resetGame`() {
        classToTest.onResetClicked()
        verify(fakeGameUseCase).resetGame()
    }

}