package ru.dmdev.cocktails.di.components

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import ru.dmdev.cocktails.CocktailsApp
import ru.dmdev.cocktails.di.modules.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        ActivitiesModule::class,
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        SearchFragmentModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {
    fun inject(application: CocktailsApp)
}