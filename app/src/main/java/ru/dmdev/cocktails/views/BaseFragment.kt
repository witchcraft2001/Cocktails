package ru.dmdev.cocktails.views

import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import ru.dmdev.cocktails.navigation.NavigatorHolder
import javax.inject.Inject

abstract class BaseFragment: Fragment() {
//    @Inject
//    lateinit var cicerone: Cicerone<Router>
//
//    @Inject
//    lateinit var navigator: NavigatorHolder

    abstract val navigationKey: String

//    val component: AppComponent? by lazy {
//        context?.let {
//            DaggerAppComponent.builder()
//                .navigationModule(NavigationModule(activity as FragmentActivity, childFragmentManager, R.id.sceneContainer))
//                .build()
//        }
//    }

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        component?.inject(this)
//
//        return inflater.inflate(R.layout.fragment_main, container, false)
//            ?: super.onCreateView(inflater, container, savedInstanceState)
//    }

//    //sends backPressed events from Main Activity to child fragments
//    override fun onBackPressed(): Boolean {
//        return if(isAdded) {
//            val childFragment = childFragmentManager.findFragmentById(R.id.sceneContainer)
//            childFragment != null && childFragment is BackButtonListener && childFragment.onBackPressed()
//        } else false
//    }
}