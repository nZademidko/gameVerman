package ru.susu.scsusu.presentation.base

sealed class ResultWrapper<out T> {
    class Success<out T>(val data: T) : ResultWrapper<T>()
    class Error(val throwable: String) : ResultWrapper<Nothing>()
}