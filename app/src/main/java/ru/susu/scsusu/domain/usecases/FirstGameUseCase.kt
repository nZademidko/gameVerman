package ru.susu.scsusu.domain.usecases

import ru.susu.scsusu.presentation.firstgame.FirstGameViewModel.FirstGameViewState

interface FirstGameUseCase {

    fun generateProblem(state: FirstGameViewState): FirstGameViewState
}