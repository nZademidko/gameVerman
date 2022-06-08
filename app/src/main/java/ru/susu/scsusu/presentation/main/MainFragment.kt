package ru.susu.scsusu.presentation.main

import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.susu.scsusu.R
import ru.susu.scsusu.databinding.FragmentMainBinding
import ru.susu.scsusu.extensions.navigate
import ru.susu.scsusu.presentation.base.BaseFragment


@AndroidEntryPoint
class MainFragment() : BaseFragment(R.layout.fragment_main) {

    private val binding by viewBinding<FragmentMainBinding>()

    override val viewModel by viewModels<MainViewModel>()

    override fun initView() {
        with(binding) {
            btnPlot.setOnClickListener {
                navigate(R.id.toPlots)
            }

            btnWorkout.setOnClickListener {
                navigate(R.id.toWorkout)
            }
        }
    }
}
