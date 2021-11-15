package ru.dmdev.cocktails.di.components

import dagger.Component
import ru.dmdev.cocktails.MainActivity
import ru.dmdev.cocktails.di.modules.*
import ru.dmdev.cocktails.views.BaseFragment
import ru.dmdev.cocktails.views.CocktailsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [
//    NavigationModule::class,
    NetworkModule::class,
    RepositoryModule::class
])
interface AppComponent {
//    fun inject(activity: MainActivity)
}