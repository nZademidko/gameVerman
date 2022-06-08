package ru.susu.scsusu.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

inline fun <T> Fragment.observe(flow: Flow<T>, crossinline onChanged: (T) -> Unit) {
    flow.onEach { onChanged(it) }.launchWhenStarted(lifecycleScope = lifecycleScope)
}

fun Fragment.navigate(directions: Int) = findNavController().navigate(directions)

fun Fragment.navigate(directions: NavDirections) = findNavController().navigate(directions)
