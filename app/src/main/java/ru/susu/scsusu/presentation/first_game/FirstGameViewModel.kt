package ru.susu.scsusu.presentation.first_game

import android.os.Parcelable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.parcelize.Parcelize
import ru.susu.scsusu.domain.usecases.FirstGameUseCase
import ru.susu.scsusu.extensions.mapDistinct
import ru.susu.scsusu.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class FirstGameViewModel @Inject constructor(
    private val firstGameUseCase: FirstGameUseCase
) : BaseViewModel() {

    private val viewState = MutableStateFlow(FirstGameViewState())
    val operationStringState = viewState.mapDistinct { it.operationString }
    val isAnswerCorrectState = viewState.mapDistinct { it.isAnswerCorrect }

    init {
        getNewProblem()
    }

    fun getNewProblem() {
        with(viewState.value) {
            val newViewState = viewState.value

            newViewState.level += 1
            if (level % 4 == 0) {
                newViewState.difficulty += 1
                newViewState.level = 2
                if (newViewState.difficulty == 4) newViewState.difficulty = 0
            }
            viewState.value = firstGameUseCase.generateProblem(newViewState)
        }
    }

    fun onButtonClicked(answer: String) {
        viewState.value = viewState.value.copy(isAnswerCorrect = 0)
        if (answer == viewState.value.correctAnswer) {
            viewState.value = viewState.value.copy(isAnswerCorrect = 1)
        } else {
            viewState.value = viewState.value.copy(isAnswerCorrect = -1)
        }
    }

    @Parcelize
    data class FirstGameViewState(
        var level: Int = 1,
        var difficulty: Int = 0,
        var operationString: String = "",
        var correctAnswer: String = "",
        var isAnswerCorrect: Int = 0
    ) : Parcelable

}