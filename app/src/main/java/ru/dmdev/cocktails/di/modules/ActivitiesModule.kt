package ru.dmdev.cocktails.di.modules

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import ru.dmdev.cocktails.MainActivity
import ru.dmdev.cocktails.di.ActivityScope

@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract fun contributesActivityAndroidInjector(): MainActivity
}