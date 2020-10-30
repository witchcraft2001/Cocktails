package ru.dmdev.cocktails.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.dmdev.cocktails.di.ViewModelKey
import ru.dmdev.cocktails.viewmodels.MainViewModel

@Module
abstract class MainActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun mainViewModel(viewModel: MainViewModel): ViewModel
}