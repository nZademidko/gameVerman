package ru.susu.scsusu.domain.usecases

import ru.susu.scsusu.presentation.plots.GameScreenViewModel.GameScreenViewState

interface GameScreenUseCase {

    fun getData(resId: Int): GameScreenViewState
}