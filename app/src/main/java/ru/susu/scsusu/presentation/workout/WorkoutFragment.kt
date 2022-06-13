package ru.susu.scsusu.presentation.workout

import androidx.fragment.app.viewModels
import com.redmadrobot.extensions.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.susu.scsusu.R
import ru.susu.scsusu.databinding.FragmentWorkoutBinding
import ru.susu.scsusu.extensions.navigate
import ru.susu.scsusu.presentation.base.BaseFragment

@AndroidEntryPoint
class WorkoutFragment : BaseFragment(R.layout.fragment_workout) {

    override val viewModel by viewModels<WorkoutViewModel>()
    private val binding by viewBinding<FragmentWorkoutBinding>()

    override fun initView() {
        with(binding) {
            btnFirstGame.setOnClickListener {
                navigate(WorkoutFragmentDirections.toFirstGame())
            }

            btnSecondGame.setOnClickListener {
                navigate(WorkoutFragmentDirections.toSecondGame())
            }

            btnThirdGame.setOnClickListener {
                navigate(WorkoutFragmentDirections.toThirdGame())
            }
        }
    }
}