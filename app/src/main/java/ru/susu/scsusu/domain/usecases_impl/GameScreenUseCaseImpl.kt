package ru.susu.scsusu.domain.usecases_impl

import ru.susu.scsusu.domain.dataMap
import ru.susu.scsusu.domain.usecases.GameScreenUseCase
import ru.susu.scsusu.presentation.plots.GameScreenViewModel.GameScreenViewState
import javax.inject.Inject

class GameScreenUseCaseImpl @Inject constructor() : GameScreenUseCase {

    override fun getData(resId: Int) =
        if (dataMap.containsKey(resId)) GameScreenViewState(
            currentResourceId = resId, options = dataMap[resId]!!
        )
        else GameScreenViewState(currentResourceId = resId, options = emptyList())

}