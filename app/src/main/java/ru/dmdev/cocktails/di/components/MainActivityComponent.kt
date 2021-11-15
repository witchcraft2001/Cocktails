package ru.dmdev.cocktails.di.components

import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Component
import dagger.Provides
import ru.dmdev.cocktails.MainActivity
import ru.dmdev.cocktails.api.CocktailsApi
import ru.dmdev.cocktails.di.PerFlow
import ru.dmdev.cocktails.di.ViewModelFactory
import ru.dmdev.cocktails.di.modules.CocktailsFragmentModule
import ru.dmdev.cocktails.di.modules.NavigationModule
import ru.dmdev.cocktails.di.modules.ViewModelModule
import ru.dmdev.cocktails.repositories.CategoriesRepository
import ru.dmdev.cocktails.views.BaseFragment
import ru.dmdev.cocktails.views.CocktailsFragment

@PerFlow
@Component(
        dependencies = [AppComponent::class],
        modules = [
            NavigationModule::class,
            ViewModelModule::class,
            CocktailsFragmentModule::class
        ])
interface MainActivityComponent : AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(baseFragment: BaseFragment)
    fun inject(cocktailsFragment: CocktailsFragment)

//    fun getCategoryRepository(): CategoriesRepository
//    fun provideCicerone(): Cicerone<Router>
//    fun router(): Router
}