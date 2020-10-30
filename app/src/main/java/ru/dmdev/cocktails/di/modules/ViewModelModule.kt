package ru.dmdev.cocktails.di.modules

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.dmdev.cocktails.di.ViewModelFactory

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}