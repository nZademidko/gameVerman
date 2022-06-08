package ru.susu.scsusu.domain.usecases_impl

import ru.susu.scsusu.domain.usecases.SecondGameUseCase
import ru.susu.scsusu.presentation.second_game.SecondGameViewModel.SecondGameViewState
import javax.inject.Inject
import kotlin.random.Random

class SecondGameUseCaseImpl @Inject constructor() : SecondGameUseCase {

    override fun generateProblem(level: Int): SecondGameViewState {
        var problem = ""
        for (i in 1..level) {
            problem += Random.nextInt(1, 10)
        }
        return SecondGameViewState(level = level, problem = problem, position = 0)
    }
}