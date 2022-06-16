package ru.susu.scsusu.presentation.third_game

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import ru.susu.scsusu.domain.usecases.ThirdGameUseCase
import ru.susu.scsusu.extensions.mapDistinct
import ru.susu.scsusu.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ThirdGameViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val thirdGameUseCase: ThirdGameUseCase
) : BaseViewModel() {

    private val args = ThirdGameFragmentArgs.fromSavedStateHandle(savedStateHandle)

    private val viewState = MutableStateFlow(ThirdGameViewState())
    val arrLState = viewState.mapDistinct { it.arrL }
    val arrRState = viewState.mapDistinct { it.arrR }
    val curLState = viewState.mapDistinct { it.curL }
    val curRState = viewState.mapDistinct { it.curR }
    val twoDigitsIsCorrectState = MutableStateFlow(TwoDigitsIsCorrectState())
    val navAction = MutableSharedFlow<Boolean>()
    var time = args.time
    val minTime = 15000L

    init {
        generateProblem()
    }

    fun generateProblem() {
        viewState.value = thirdGameUseCase.generateProblem()
        if (time > minTime) time-=1000
    }

    fun onLeftSideClicked(digit: Int) {
        viewState.value = viewState.value.copy(curL = digit)
        checkDigits()
    }

    fun onRightSideClicked(digit: Int) {
        viewState.value = viewState.value.copy(curR = digit)
        checkDigits()
    }

    private fun checkDigits() {
        with(viewState.value) {
            if (curL != -1 && curL == curR) {
                val newArrL = viewState.value.arrL.toMutableMap()
                val newArrR = viewState.value.arrR.toMutableMap()
                newArrL["$curL"] = true
                newArrR["$curR"] = true
                viewState.value = viewState.value.copy(
                    arrL = newArrL,
                    arrR = newArrR,
                )
                val isAllCorrect = newArrL.all { it.value }

                if (isAllCorrect) {
                    if (args.resId == -1) {
                        generateProblem()
                    } else {
                        viewModelScope.launch {
                            navAction.emit(true)
                        }
                    }
                } else {
                    viewModelScope.launch {
                        twoDigitsIsCorrectState.value =
                            twoDigitsIsCorrectState.value.copy(
                                leftDigit = curL,
                                rightDigit = curR,
                                action = 1
                            )
                        delay(500)
                        twoDigitsIsCorrectState.value =
                            twoDigitsIsCorrectState.value.copy(
                                leftDigit = curL,
                                rightDigit = curR,
                                action = 0
                            )

                        viewState.value = viewState.value.copy(
                            curL = -1,
                            curR = -1
                        )
                    }
                }
            } else if (curL != -1 && curR != -1 && curR != curL) {
                viewModelScope.launch {
                    twoDigitsIsCorrectState.value =
                        twoDigitsIsCorrectState.value.copy(
                            leftDigit = curL,
                            rightDigit = curR,
                            action = -1
                        )
                    delay(500)
                    if (args.resId == -1) {
                        generateProblem()
                    } else {
                        viewModelScope.launch {
                            navAction.emit(false)
                        }
                    }
                }
            } else {

            }
        }
    }

    @Parcelize
    data class ThirdGameViewState(
        val arrL: Map<String, Boolean> = mapOf(),
        val arrR: Map<String, Boolean> = mapOf(),
        val curL: Int = -1,
        val curR: Int = -1,
    ) : Parcelable

    @Parcelize
    data class TwoDigitsIsCorrectState(
        val leftDigit: Int = -1,
        val rightDigit: Int = -1,
        val action: Int = -1, // -1 - неправильно, 1 - правильно, 0 - удалить
    ) : Parcelable
}