package ru.susu.scsusu.presentation.plots

import androidx.fragment.app.viewModels
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.susu.scsusu.R
import ru.susu.scsusu.databinding.FragmentPlotsBinding
import ru.susu.scsusu.presentation.base.BaseFragment
import ru.susu.scsusu.presentation.base.BaseViewModel

@AndroidEntryPoint
class PlotsFragment : BaseFragment(R.layout.fragment_plots) {

    override val viewModel by viewModels<BaseViewModel>()
    private val binding by viewBinding<FragmentPlotsBinding>()


}