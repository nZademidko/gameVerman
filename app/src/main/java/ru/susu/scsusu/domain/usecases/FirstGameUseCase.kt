package ru.susu.scsusu.domain.usecases

import ru.susu.scsusu.presentation.first_game.FirstGameViewModel.FirstGameViewState

interface FirstGameUseCase {

    fun generateProblem(state: FirstGameViewState): FirstGameViewState
}