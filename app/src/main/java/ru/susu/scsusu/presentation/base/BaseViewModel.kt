package ru.susu.scsusu.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow

abstract class BaseViewModel : ViewModel() {
//
//    val baseState = MutableStateFlow<BaseState>(BaseState.Progress)
//
//    protected fun <T> doSafeWork(
//        doOnProgress: () -> Unit = ::defaultOnProgress,
//        doOnAsyncBlock: suspend CoroutineScope.() -> T,
//        doOnSuccess: (ResultWrapper.Success<T>) -> Unit = { },
//        doOnError: (ResultWrapper.Error) -> Unit = ::defaultOnError
//    ): Job = viewModelScope.launch {
//        doOnProgress.invoke()
//        val result = withContext(Dispatchers.IO) {
//            try {
//                val response = doOnAsyncBlock.invoke(this)
//                ResultWrapper.Success(response)
//            } catch (e: Exception) {
//                ResultWrapper.Error(e.toString())
//            }
//        }
//        when (result) {
//            is ResultWrapper.Success -> doOnSuccess.invoke(result)
//            is ResultWrapper.Error -> doOnError.invoke(result)
//        }
//    }
//
//    private fun defaultOnProgress() {
//        baseState.value = BaseState.Progress
//    }
//
//    protected fun defaultOnError(result: ResultWrapper.Error) {
//        baseState.value = BaseState.ScreenError(result.throwable)
//    }
//
//    sealed class BaseState {
//        object Content : BaseState()
//        object Progress : BaseState()
//        class ScreenError(throwable: String) : BaseState()
//    }
}