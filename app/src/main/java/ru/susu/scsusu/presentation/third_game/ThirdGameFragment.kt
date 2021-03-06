package ru.susu.scsusu.presentation.third_game

import android.annotation.SuppressLint
import android.os.CountDownTimer
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.susu.scsusu.R
import ru.susu.scsusu.databinding.FragmentGameThirdBinding
import ru.susu.scsusu.extensions.navigate
import ru.susu.scsusu.extensions.observe
import ru.susu.scsusu.presentation.base.BaseFragment
import ru.susu.scsusu.presentation.first_game.FirstGameFragmentDirections
import ru.susu.scsusu.presentation.second_game.SecondGameFragmentArgs
import ru.susu.scsusu.presentation.second_game.SecondGameFragmentDirections

@AndroidEntryPoint
class ThirdGameFragment : BaseFragment(R.layout.fragment_game_third) {

    override val viewModel by viewModels<ThirdGameViewModel>()
    private val binding by viewBinding<FragmentGameThirdBinding>()
    private val args by navArgs<ThirdGameFragmentArgs>()
    private var timer: CountDownTimer? = null

    private val arrL by lazy {
        with(binding) {
            listOf(l0, l1, l2, l3, l4, l5, l6, l7, l8, l9)
        }
    }

    private val arrR by lazy {
        with(binding) {
            listOf(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9)
        }
    }

    override fun initView() {
        startTimer(viewModel.time)
        with(binding) {
            arrL.forEach { tv ->
                tv.setOnClickListener {
                    viewModel.onLeftSideClicked(tv.text.toString().toInt())
                }
            }
            arrR.forEach { tv ->
                tv.setOnClickListener {
                    viewModel.onRightSideClicked(tv.text.toString().toInt())
                }
            }
        }
    }

    override fun initObservers() {
        observe(viewModel.arrLState) { v ->
            var count = 0
            v.forEach { entry ->
                if (!entry.value) arrL[count].isVisible = true
                arrL[count].text = entry.key
                count++
            }
        }

        observe(viewModel.arrRState) { v ->
            var count = 0
            v.forEach { entry ->
                if (!entry.value) arrR[count].isVisible = true
                arrR[count].text = entry.key
                count++
            }
        }

        observe(viewModel.curLState) { v ->
            arrL.forEach {
                if (it.text.toString().toInt() == v) {
                    it.setBackgroundResource(R.drawable.background_letter_yellow)
                } else {
                    it.setBackgroundResource(R.drawable.background_letter_grey)
                }
                it.isEnabled = v == -1
            }
        }

        observe(viewModel.curRState) { v ->
            arrR.forEach {
                if (it.text.toString().toInt() == v) {
                    it.setBackgroundResource(R.drawable.background_letter_yellow)
                } else {
                    it.setBackgroundResource(R.drawable.background_letter_grey)
                }
                it.isEnabled = v == -1
            }
        }

        observe(viewModel.twoDigitsIsCorrectState) { state ->
            arrL.forEachIndexed { index, _ ->
                when (state.action) {
                    -1 -> {
                        if (state.leftDigit == arrL[index].text.toString().toInt()) {
                            arrL[index].setBackgroundResource(R.drawable.background_letter_red)
                        }
                        if (state.rightDigit == arrR[index].text.toString().toInt()) {
                            arrR[index].setBackgroundResource(R.drawable.background_letter_red)
                        }
                    }
                    0 -> {
                        if (state.leftDigit == arrL[index].text.toString().toInt()) {
                            arrL[index].isVisible = false
                        }
                        if (state.rightDigit == arrR[index].text.toString().toInt()) {
                            arrR[index].isVisible = false
                        }
                    }
                    1 -> {
                        if (state.leftDigit == arrL[index].text.toString().toInt()) {
                            arrL[index].setBackgroundResource(R.drawable.background_letter_green)
                        }
                        if (state.rightDigit == arrR[index].text.toString().toInt()) {
                            arrR[index].setBackgroundResource(R.drawable.background_letter_green)
                        }
                    }
                }
            }
        }

        observe(viewModel.navAction) { isCorrect ->
            if (isCorrect) {
                Snackbar.make(requireView(), "?????? ??????????????!!!", Snackbar.LENGTH_LONG).show()
                navigate(ThirdGameFragmentDirections.toGameScreen(args.resId))
            } else {
                Snackbar.make(requireView(), "?? ??????????????????, ???? ??????????????????", Snackbar.LENGTH_LONG)
                    .show()
                navigate(ThirdGameFragmentDirections.toPlots())
            }
        }
    }

    private fun startTimer(timeMillis: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(timeMillis, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(p0: Long) {
                binding.timerText.text = "???????????????? " + (p0 / 1000).toString() + " ????????????"
            }

            override fun onFinish() {
                Snackbar.make(requireView(), "?? ??????????????????, ???? ??????????????????", Snackbar.LENGTH_LONG)
                    .show()
                timer?.cancel()
                navigate(ThirdGameFragmentDirections.toPlots())
            }
        }.start()
    }

    override fun onStop() {
        super.onStop()
        timer?.cancel()
    }
}