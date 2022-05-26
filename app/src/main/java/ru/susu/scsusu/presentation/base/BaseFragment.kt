package ru.susu.scsusu.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import ru.susu.scsusu.extensions.hideSoftInput
import ru.susu.scsusu.extensions.observe

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {

    abstract val viewModel: BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBaseObservers()
        initObservers()
        initView()
        view.hideSoftInput()
    }

    protected open fun initObservers() {}

    protected open fun initView() {}

    private fun initBaseObservers() {
//        observe(viewModel.baseState) { state ->
//            when (state) {
//                is BaseViewModel.BaseState.Content -> {
//                    showContentState()
//                }
//                is BaseViewModel.BaseState.Progress -> {
//                    showProgressState()
//                }
//                is BaseViewModel.BaseState.ScreenError -> {
//                    showScreenErrorState()
//                }
//            }
//        }
    }

    protected open fun showContentState() {

    }

    protected open fun showProgressState() {

    }

    protected open fun showScreenErrorState() {

    }
}