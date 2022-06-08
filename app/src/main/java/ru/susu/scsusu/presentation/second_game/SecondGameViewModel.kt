package ru.susu.scsusu.presentation.second_game

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import ru.susu.scsusu.domain.usecases.SecondGameUseCase
import ru.susu.scsusu.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SecondGameViewModel @Inject constructor(
    private val secondGameUseCase: SecondGameUseCase
) : BaseViewModel() {

    val viewState = MutableStateFlow(SecondGameViewState())
    val showRightSeqState = MutableStateFlow(0)
    val isEnabledDigitsState = MutableStateFlow(false)
    val isChosenDigitRightState = MutableStateFlow(ChosenDigitViewState())
    val showToastActionFlow = MutableSharedFlow<String>()

    init {
        generateProblem(3)
    }

    fun generateProblem(level: Int) {
        isEnabledDigitsState.value = false
        viewState.value = secondGameUseCase.generateProblem(level)
        viewModelScope.launch {
            for (i in 0 until viewState.value.problem.length) {
                delay(700)
                showRightSeqState.value = viewState.value.problem[i].digitToInt()
                delay(700)
                showRightSeqState.value = 0
            }
            isEnabledDigitsState.value = true
        }
    }

    fun onBtnClicked(index: Int) {
        viewModelScope.launch {
            isEnabledDigitsState.value = false
            if (index == viewState.value.problem[viewState.value.position].digitToInt()) {
                isChosenDigitRightState.value =
                    isChosenDigitRightState.value.copy(digit = index, isRight = true)
                delay(300)
                isChosenDigitRightState.value =
                    isChosenDigitRightState.value.copy(digit = 0, isRight = true)
                viewState.value = viewState.value.copy(position = viewState.value.position + 1)
                isEnabledDigitsState.value = true
                if (viewState.value.position == viewState.value.problem.length ) {
                    showToastActionFlow.emit("Получилось!!!")
                    generateProblem(viewState.value.level + 1)
                }
            } else {
                isChosenDigitRightState.value =
                    isChosenDigitRightState.value.copy(digit = index, isRight = false)
                showToastActionFlow.emit("Неправильно, давай сначала!")
                generateProblem(viewState.value.level)
            }
        }
    }

    @Parcelize
    data class SecondGameViewState(
        val level: Int = 3,
        val problem: String = "",
        val position: Int = 0
    ) : Parcelable

    @Parcelize
    data class ChosenDigitViewState(
        val digit: Int = 0,
        val isRight: Boolean = false
    ) : Parcelable
}