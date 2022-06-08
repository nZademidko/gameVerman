package ru.susu.scsusu.presentation.plots

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.parcelize.Parcelize
import ru.susu.scsusu.domain.ButtonRes
import ru.susu.scsusu.domain.usecases.GameScreenUseCase
import ru.susu.scsusu.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class GameScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val gameScreenUseCase: GameScreenUseCase
) : BaseViewModel() {

    val viewState = MutableStateFlow(GameScreenViewState())
    private val args = GameScreenFragmentArgs.fromSavedStateHandle(savedStateHandle)

    init {
        setPart(args.resourceId)
    }

    fun setPart(resourceId: Int) {
        viewState.value = gameScreenUseCase.getData(resId = resourceId)
    }

    fun onClicked(resId: Int) {
        setPart(resId)
    }

    @Parcelize
    data class GameScreenViewState(
        val currentResourceId: Int = -1,
        val options: List<ButtonRes> = emptyList()
    ) : Parcelable
}