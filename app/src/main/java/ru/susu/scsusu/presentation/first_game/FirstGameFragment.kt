package ru.susu.scsusu.presentation.first_game

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.susu.scsusu.R
import ru.susu.scsusu.databinding.FragmentGameFirstBinding
import ru.susu.scsusu.extensions.observe
import ru.susu.scsusu.presentation.base.BaseFragment

@AndroidEntryPoint
class FirstGameFragment : BaseFragment(R.layout.fragment_game_first) {

    override val viewModel by viewModels<FirstGameViewModel>()
    private val binding by viewBinding<FragmentGameFirstBinding>()

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
                Toast.makeText(requireContext(), "Right!", Toast.LENGTH_SHORT).show()
                viewModel.getNewProblem()
            } else if (state == -1) {
                Toast.makeText(requireContext(), "Not right!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}