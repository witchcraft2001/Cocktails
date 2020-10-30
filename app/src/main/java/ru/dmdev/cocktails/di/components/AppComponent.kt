package ru.dmdev.cocktails.di.components

import dagger.Component
import ru.dmdev.cocktails.MainActivity
import ru.dmdev.cocktails.di.modules.MainActivityModule
import ru.dmdev.cocktails.di.modules.NetworkModule
import ru.dmdev.cocktails.di.modules.RepositoryModule
import ru.dmdev.cocktails.di.modules.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    RepositoryModule::class,
    ViewModelModule::class,
    MainActivityModule::class
])
interface AppComponent {
    fun inject(activity: MainActivity)
}