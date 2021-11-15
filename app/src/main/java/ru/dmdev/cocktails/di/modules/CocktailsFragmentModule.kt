package ru.dmdev.cocktails.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.dmdev.cocktails.di.ViewModelKey
import ru.dmdev.cocktails.viewmodels.CocktailsViewModel

@Module
abstract class CocktailsFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(CocktailsViewModel::class)
    internal abstract fun cocktailsViewModel(viewModel: CocktailsViewModel): ViewModel
}