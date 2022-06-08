package ru.susu.scsusu.presentation.plots

import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.viewModels
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.susu.scsusu.R
import ru.susu.scsusu.databinding.FragmentScreenGameBinding
import ru.susu.scsusu.extensions.navigate
import ru.susu.scsusu.extensions.observe
import ru.susu.scsusu.presentation.base.BaseFragment

@AndroidEntryPoint
class GameScreenFragment : BaseFragment(R.layout.fragment_screen_game) {

    override val viewModel by viewModels<GameScreenViewModel>()
    private val binding by viewBinding<FragmentScreenGameBinding>()

    private val adapter by lazy {
        QuestAdapter() {
            when (it.gameType) {
                GameType.NOTHING -> viewModel.onClicked(it.navigationId)
                GameType.MATH_PROBLEM -> navigate(
                    GameScreenFragmentDirections.toFirstGame(
                        level = it.level,
                        maxLevel = it.maxLevel,
                        resId = it.navigationId
                    )
                )
                GameType.REPEAT_SEQUENCE -> navigate(
                    GameScreenFragmentDirections.toSecondGame(
                        level = it.level,
                        maxLevel = it.maxLevel,
                        resId = it.navigationId
                    )
                )
            }
        }
    }

    override fun initView() {
        with(binding) {
            rv.adapter = adapter
            questText.movementMethod = ScrollingMovementMethod()
        }
    }

    override fun initObservers() {
        observe(viewModel.viewState) { state ->
            with(binding) {
                if (state.currentResourceId != -1) {
                    if (state.currentResourceId == 1000) {
                        navigate(GameScreenFragmentDirections.toPlots())
                    } else {
                        questText.text = getString(state.currentResourceId)
                        adapter.setData(state.options.toMutableList())
                    }
                }
            }
        }
    }
}