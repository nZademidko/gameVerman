package ru.susu.scsusu.presentation.first_game

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import ru.susu.scsusu.domain.usecases.FirstGameUseCase
import ru.susu.scsusu.extensions.mapDistinct
import ru.susu.scsusu.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class FirstGameViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val firstGameUseCase: FirstGameUseCase
) : BaseViewModel() {

    private val args = FirstGameFragmentArgs.fromSavedStateHandle(savedStateHandle)

    val viewState =
        MutableStateFlow(FirstGameViewState(level = if (args.level >= 1) args.level else 1))
    val operationStringState = viewState.mapDistinct { it.operationString }
    val isAnswerCorrectState = viewState.mapDistinct { it.isAnswerCorrect }
    val navAction = MutableSharedFlow<Boolean>()

    init {
        getNewProblem()
    }

    fun getNewProblem() {
        with(viewState.value) {
            val newViewState = viewState.value

            val maxLevel = if (args.maxLevel < 1) {
                4
            } else {
                args.maxLevel
            }
            newViewState.level += 1
            if (level % maxLevel == 0) {
                if (args.resId != -1) {
                    viewModelScope.launch {
                        navAction.emit(true)
                    }
                } else {
                    newViewState.difficulty += 1
                    newViewState.level = 2
                    if (newViewState.difficulty == 4) newViewState.difficulty = 0
                }
            }
            viewState.value = firstGameUseCase.generateProblem(newViewState)
        }
    }

    fun onButtonClicked(answer: String) {
        viewState.value = viewState.value.copy(isAnswerCorrect = 0)

        if (answer == viewState.value.correctAnswer) {
            viewState.value = viewState.value.copy(isAnswerCorrect = 1)
        } else {
            if (args.resId != -1) {
                viewModelScope.launch {
                    navAction.emit(false)
                }
            } else {
                viewState.value = viewState.value.copy(isAnswerCorrect = -1)
            }
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