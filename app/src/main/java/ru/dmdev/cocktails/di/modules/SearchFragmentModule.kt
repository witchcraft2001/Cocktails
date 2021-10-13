package ru.dmdev.cocktails.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import ru.dmdev.cocktails.di.ViewModelKey
import ru.dmdev.cocktails.screens.search.SearchFragment
import ru.dmdev.cocktails.screens.search.SearchViewModel

@Module
abstract class SearchFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal abstract fun searchViewModel(viewModel: SearchViewModel): ViewModel
    @ContributesAndroidInjector
    internal abstract fun bindFragment(): SearchFragment
}

