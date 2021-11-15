package ru.dmdev.cocktails.di.modules

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import ru.dmdev.cocktails.di.PerFlow
import ru.dmdev.cocktails.navigation.NavigatorHolder

@Module
class NavigationModule(val fragmentActivity: FragmentActivity, val fm: FragmentManager, val containerId: Int) {
    @Provides
    @PerFlow
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @PerFlow
    fun provideNavigator(): NavigatorHolder = NavigatorHolder(fragmentActivity, fm, containerId)

    @Provides
    @PerFlow
    fun provideCustomRouter(cicerone: Cicerone<Router>): Router = cicerone.router
}