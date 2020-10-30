package ru.dmdev.cocktails

import android.app.Application
import ru.dmdev.cocktails.di.components.DaggerAppComponent

class CocktailsApp: Application() {
    val appComponent = DaggerAppComponent.create()
}