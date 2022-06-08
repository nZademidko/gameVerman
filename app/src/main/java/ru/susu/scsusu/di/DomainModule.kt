package ru.susu.scsusu.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.susu.scsusu.domain.usecases.FirstGameUseCase
import ru.susu.scsusu.domain.usecases.GameScreenUseCase
import ru.susu.scsusu.domain.usecases.SecondGameUseCase
import ru.susu.scsusu.domain.usecases_impl.FirstGameUseCaseImpl
import ru.susu.scsusu.domain.usecases_impl.GameScreenUseCaseImpl
import ru.susu.scsusu.domain.usecases_impl.SecondGameUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindFirstGameUseCase(impl: FirstGameUseCaseImpl): FirstGameUseCase

    @Binds
    abstract fun bindSecondGameUseCase(impl: SecondGameUseCaseImpl): SecondGameUseCase

    @Binds
    abstract fun bindGameScreenUseCase(impl: GameScreenUseCaseImpl): GameScreenUseCase
}