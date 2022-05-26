package ru.susu.scsusu.presentation.firstgame

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.susu.scsusu.R
import ru.susu.scsusu.presentation.base.BaseFragment

@AndroidEntryPoint
class FirstGameFragment: BaseFragment(R.layout.fragment_game_first) {

    override val viewModel by viewModels<FirstGameViewModel>()

}