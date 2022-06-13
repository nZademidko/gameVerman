package ru.susu.scsusu.domain.usecases_impl

import ru.susu.scsusu.domain.usecases.ThirdGameUseCase
import ru.susu.scsusu.presentation.third_game.ThirdGameViewModel.ThirdGameViewState
import javax.inject.Inject

class ThirdGameUseCaseImpl @Inject constructor() : ThirdGameUseCase {

    override fun generateProblem(): ThirdGameViewState {
        val arrL = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9").shuffled()
        val arrR = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9").shuffled()
        val mapL = mutableMapOf<String, Boolean>()
        val mapR = mutableMapOf<String, Boolean>()
        arrL.forEach {
            mapL[it] = false
        }
        arrR.forEach {
            mapR[it] = false
        }

        return ThirdGameViewState(arrL = mapL, arrR = mapR)
    }
}