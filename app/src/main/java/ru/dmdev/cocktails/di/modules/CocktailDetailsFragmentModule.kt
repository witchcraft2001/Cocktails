package ru.dmdev.cocktails.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import ru.dmdev.cocktails.di.ViewModelKey
import ru.dmdev.cocktails.screens.details.CocktailDetailsFragment
import ru.dmdev.cocktails.screens.details.CocktailDetailsViewModel

@Module
abstract class CocktailDetailsFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(CocktailDetailsViewModel::class)
    internal abstract fun detailsViewModel(viewModel: CocktailDetailsViewModel): ViewModel
    @ContributesAndroidInjector
    internal abstract fun bindFragment(): CocktailDetailsFragment
}

