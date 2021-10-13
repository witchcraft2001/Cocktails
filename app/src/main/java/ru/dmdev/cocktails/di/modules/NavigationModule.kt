package ru.dmdev.cocktails.di.modules

import dagger.Module
import dagger.Provides
import ru.dmdev.cocktails.utils.ActivityUtils
import javax.inject.Singleton

@Module
class NavigationModule {
    @Provides
    @Singleton
    fun provideActivityUtils(): ActivityUtils {
        return ActivityUtils()
    }
}