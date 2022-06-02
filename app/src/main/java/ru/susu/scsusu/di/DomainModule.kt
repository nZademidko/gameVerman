package ru.susu.scsusu.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.susu.scsusu.domain.usecases.FirstGameUseCase
import ru.susu.scsusu.domain.usecases_impl.FirstGameUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindFirstGameUseCase(impl: FirstGameUseCaseImpl): FirstGameUseCase
}