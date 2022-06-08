package ru.susu.scsusu.presentation.first_game

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
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

    override fun initView() {
        binding.doneButton.setOnClickListener {
            viewModel.onButtonClicked(binding.answerField.text.toString())
        }
    }

    override fun initObservers() {
        observe(viewModel.operationStringState) { operationString ->
            binding.problemTV.text = operationString
        }

        observe(viewModel.isAnswerCorrectState) { state ->
            if (state == 1) {
                Snackbar.make(requireView(), "Right!", Snackbar.LENGTH_SHORT).show()
                viewModel.getNewProblem()
            } else if (state == -1) {
                Snackbar.make(requireView(), "Not right!", Snackbar.LENGTH_SHORT).show()
            }
        }

        observe(viewModel.navAction) { isCorrect ->
            if (isCorrect) {
                Snackbar.make(requireView(), "Вам удалось!!!", Snackbar.LENGTH_LONG).show()
                navigate(FirstGameFragmentDirections.toGameScreen(args.resId))
            } else {
                Snackbar.make(requireView(), "К сожалению вы проиграли", Snackbar.LENGTH_LONG)
                    .show()
                navigate(FirstGameFragmentDirections.toPlots())
            }
        }
    }
}