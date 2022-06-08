package ru.susu.scsusu.domain.usecases

import ru.susu.scsusu.presentation.second_game.SecondGameViewModel.SecondGameViewState

interface SecondGameUseCase {

    fun generateProblem(level: Int): SecondGameViewState
}