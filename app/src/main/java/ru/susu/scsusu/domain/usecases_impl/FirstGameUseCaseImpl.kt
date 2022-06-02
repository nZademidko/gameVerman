package ru.susu.scsusu.domain.usecases_impl

import ru.susu.scsusu.domain.usecases.FirstGameUseCase
import ru.susu.scsusu.presentation.firstgame.FirstGameViewModel.FirstGameViewState
import javax.inject.Inject
import kotlin.random.Random

class FirstGameUseCaseImpl @Inject constructor() : FirstGameUseCase {

    private var numbers = ArrayList<Int>()
    private var difficulty = 0;

    override fun generateProblem(state: FirstGameViewState): FirstGameViewState {
        var operationString = ""
        var correctAnswer = ""

        difficulty = state.difficulty
        val numbersCount = state.level
        val number1 = generateNumber()
        numbers.add(number1)
        operationString = number1.toString()
        repeat(numbersCount - 1) { operationString += getOperation(numbers.last()) }

        correctAnswer = numbers.sum().toString()
        numbers.clear()
        return state.copy(
            operationString = operationString,
            correctAnswer = correctAnswer
        )
    }

    private fun getOperation(prevNumber: Int): String {
        val number = generateNumber()
        var operation = ""
        when ((0..2).random()) {
            0 -> {
                operation = " + "
                numbers.add(number)
            }
            1 -> {
                operation = " - "
                numbers.add(-1 * number)
            }
            2 -> {
                operation = " * "
                val interNumber = number * prevNumber
                numbers.removeLast()
                numbers.add(interNumber)
            }
            else -> operation = "Something gone wrong"
        }
        return operation + number.toString()
    }

    private fun generateNumber(): Int {
        val number = when (difficulty) {
            0 -> Random.nextInt(0,10)
            1 -> Random.nextInt(1,15)
            2 -> Random.nextInt(7,20)
            3 -> Random.nextInt(-10,20)
            else -> 0
        }
        return number
    }

}