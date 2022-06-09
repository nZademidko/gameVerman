package ru.susu.scsusu.presentation.second_game

import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.susu.scsusu.R
import ru.susu.scsusu.databinding.FragmentGameSecondBinding
import ru.susu.scsusu.extensions.navigate
import ru.susu.scsusu.extensions.observe
import ru.susu.scsusu.presentation.base.BaseFragment
import ru.susu.scsusu.presentation.first_game.FirstGameFragmentArgs
import ru.susu.scsusu.presentation.first_game.FirstGameFragmentDirections

@AndroidEntryPoint
class SecondGameFragment : BaseFragment(R.layout.fragment_game_second) {

    override val viewModel by viewModels<SecondGameViewModel>()
    private val binding by viewBinding<FragmentGameSecondBinding>()
    private val buttonList = mutableListOf<TextView>()
    private val args by navArgs<SecondGameFragmentArgs>()

    override fun initView() {
        with(binding) {
            buttonList.add(one)
            buttonList.add(two)
            buttonList.add(three)
            buttonList.add(four)
            buttonList.add(five)
            buttonList.add(six)
            buttonList.add(seven)
            buttonList.add(eight)
            buttonList.add(nine)

            one.setOnClickListener {
                viewModel.onBtnClicked(1)
            }

            two.setOnClickListener {
                viewModel.onBtnClicked(2)
            }

            three.setOnClickListener {
                viewModel.onBtnClicked(3)
            }

            four.setOnClickListener {
                viewModel.onBtnClicked(4)
            }

            five.setOnClickListener {
                viewModel.onBtnClicked(5)
            }

            six.setOnClickListener {
                viewModel.onBtnClicked(6)
            }

            seven.setOnClickListener {
                viewModel.onBtnClicked(7)
            }

            eight.setOnClickListener {
                viewModel.onBtnClicked(8)
            }

            nine.setOnClickListener {
                viewModel.onBtnClicked(9)
            }
        }
    }

    override fun initObservers() {
        observe(viewModel.isEnabledDigitsState) { isEnabled ->
            buttonList.forEach { tv ->
                tv.isEnabled = isEnabled
            }
        }

        observe(viewModel.showRightSeqState) { digit ->
            buttonList.forEach { tv ->
                tv.setBackgroundResource(R.drawable.background_grey_letter_second_game)
            }

            buttonList.forEachIndexed { index, tv ->
                if (index + 1 == digit) {
                    tv.setBackgroundResource(R.drawable.background_green_letter_second_game)
                }
            }
        }

        observe(viewModel.isChosenDigitRightState) { state ->
            buttonList.forEach { tv ->
                tv.setBackgroundResource(R.drawable.background_grey_letter_second_game)
            }

            buttonList.forEachIndexed { index, tv ->
                if (index + 1 == state.digit) {
                    tv.setBackgroundResource(
                        if (state.isRight) {
                            R.drawable.background_green_letter_second_game
                        } else {
                            R.drawable.background_red_letter_second_game
                        }
                    )
                }
            }
        }

        observe(viewModel.showToastActionFlow) {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()
        }

        observe(viewModel.navAction) { isCorrect ->
            if (isCorrect) {
                Snackbar.make(requireView(), "Вам удалось!!!", Snackbar.LENGTH_LONG).show()
                navigate(SecondGameFragmentDirections.toGameScreen(args.resId))
            } else {
                Snackbar.make(requireView(), "К сожалению, Вы проиграли", Snackbar.LENGTH_LONG)
                    .show()
                navigate(SecondGameFragmentDirections.toPlots())
            }
        }
    }
}
