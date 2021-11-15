package ru.dmdev.cocktails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import ru.dmdev.cocktails.di.components.AppComponent
import ru.dmdev.cocktails.di.components.DaggerAppComponent
import ru.dmdev.cocktails.di.components.MainActivityComponent
import ru.dmdev.cocktails.di.modules.NavigationModule
import ru.dmdev.cocktails.navigation.NavigatorHolder
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var cicerone: Cicerone<Router>

    @Inject
    lateinit var navigator: NavigatorHolder

    val appComponent: AppComponent by lazy { DaggerAppComponent.builder().build() }

    companion object {
        lateinit var instance: MainActivity
    }

    val component: MainActivityComponent? by lazy {
        applicationContext?.let {
            DaggerMainActivityComponent.builder()
                    .applicationComponent(CocktailsApp.instance.appComponent)
                    .navigationModule(NavigationModule(this as FragmentActivity, supportFragmentManager, R.id.sceneContainer))
                    .build()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        instance = this

        component?.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        cicerone.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        cicerone.getNavigatorHolder().removeNavigator()
        super.onPause()
    }
}