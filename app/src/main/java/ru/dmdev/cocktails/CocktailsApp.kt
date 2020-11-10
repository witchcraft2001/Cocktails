package ru.dmdev.cocktails

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ru.dmdev.cocktails.di.components.DaggerAppComponent
import javax.inject.Inject

class CocktailsApp: Application(), HasAndroidInjector {
    @Inject lateinit var androidInjector : DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.create()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}