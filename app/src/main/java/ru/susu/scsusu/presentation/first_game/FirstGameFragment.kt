package ru.susu.scsusu.presentation.first_game

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import ru.susu.scsusu.R
import ru.susu.scsusu.databinding.FragmentGameFirstBinding
import ru.susu.scsusu.extensions.navigate
import ru.susu.scsusu.extensions.observe
import ru.susu.scsusu.presentation.base.BaseFragment

@AndroidEntryPoint
class FirstGameFragment : BaseFragment(R.layout.fragment_game_first) {

    override val viewModel by viewModels<FirstGameViewModel>()
    private val binding by viewBinding<FragmentGameFirstBinding>()
    private val args by navArgs<FirstGameFragmentArgs>()
    private var timer: CountDownTimer? = null

    override fun initView() {

        startTimer(15000)

        binding.doneButton.setOnClickListener {
            viewModel.onButtonClicked(binding.answerField.text.toString())
            startTimer(15000)
        }
        binding.refreshButton.setOnClickListener {
            viewModel.viewState.value.level -= 1
            viewModel.getNewProblem()
            startTimer(15000)
            it.isVisible = false
        }
    }

    override fun initObservers() {
        observe(viewModel.operationStringState) { operationString ->
            binding.problemTV.text = operationString
        }

        observe(viewModel.isAnswerCorrectState) { state ->
            if (state == 1) {
                Snackbar.make(requireView(), "Правильно!", Snackbar.LENGTH_SHORT).show()
                viewModel.getNewProblem()
            } else if (state == -1) {
                Snackbar.make(requireView(), "Неправильно!", Snackbar.LENGTH_SHORT).show()
            }
        }

        observe(viewModel.navAction) { isCorrect ->
            if (isCorrect) {
                Snackbar.make(requireView(), "Вам удалось!!!", Snackbar.LENGTH_LONG).show()
                timer?.cancel()
                navigate(FirstGameFragmentDirections.toGameScreen(args.resId))
            } else {
                Snackbar.make(requireView(), "К сожалению, Вы проиграли", Snackbar.LENGTH_LONG)
                    .show()
                timer?.cancel()
                navigate(FirstGameFragmentDirections.toPlots())
            }
        }
    }

    private fun startTimer(timeMillis: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(timeMillis, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(p0: Long) {
                binding.timerText.text = "Осталось " + (p0 / 1000).toString() + " секунд"
            }

            override fun onFinish() {
                Snackbar.make(requireView(), "К сожалению, Вы проиграли", Snackbar.LENGTH_LONG)
                    .show()
                timer?.cancel()
                navigate(FirstGameFragmentDirections.toPlots())
            }
        }.start()
    }

    override fun onStop() {
        super.onStop()
        timer?.cancel()
    }
}