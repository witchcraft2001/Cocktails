package ru.dmdev.cocktails

import android.app.Application
import ru.dmdev.cocktails.di.components.AppComponent
import ru.dmdev.cocktails.di.components.DaggerAppComponent

class CocktailsApp: Application() {
    val appComponent: AppComponent by lazy { DaggerAppComponent.builder().build() }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: CocktailsApp
    }
}