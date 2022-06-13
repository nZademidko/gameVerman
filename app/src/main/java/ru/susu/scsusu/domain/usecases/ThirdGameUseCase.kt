package ru.susu.scsusu.domain.usecases

import ru.susu.scsusu.presentation.third_game.ThirdGameViewModel.ThirdGameViewState

interface ThirdGameUseCase {

    fun generateProblem(): ThirdGameViewState
}