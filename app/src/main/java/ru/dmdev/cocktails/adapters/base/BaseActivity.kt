package ru.dmdev.cocktails.adapters.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity: DaggerAppCompatActivity() {
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentHandler: FragmentHandler

    private val navigationBackPressListener = View.OnClickListener { fragmentManager.popBackStack() }

    private val backStackListener = FragmentManager.OnBackStackChangedListener { syncDrawerToggleState() }

    private fun syncDrawerToggleState() {
        val drawerToggle = getDrawerToggle() ?: return

        if (fragmentManager.backStackEntryCount > 1) {
            drawerToggle.isDrawerIndicatorEnabled = false
            drawerToggle.toolbarNavigationClickListener = navigationBackPressListener
        } else {
            drawerToggle.isDrawerIndicatorEnabled = true
            drawerToggle.toolbarNavigationClickListener = drawerToggle.toolbarNavigationClickListener
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        fragmentManager = supportFragmentManager
        fragmentHandler = FragmentHandler(fragmentManager)
        fragmentManager.addOnBackStackChangedListener(backStackListener)

        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        getDrawer()?.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerStateChanged(newState: Int) {
            }

            override fun onDrawerSlide(view: View, p1: Float) {
                syncDrawerToggleState()
            }

            override fun onDrawerClosed(view: View) {
                syncDrawerToggleState()
            }

            override fun onDrawerOpened(view: View) {
                syncDrawerToggleState()
            }
        })
    }

    override fun onDestroy() {
        fragmentManager.removeOnBackStackChangedListener(backStackListener)
        //todo:Разобраться с обнулением
        //fragmentManager = null
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (sendBackPressToDrawer())
            return

        if (sendBackPressToFragmentOnTop())
            return

        super.onBackPressed()

        if (fragmentManager.backStackEntryCount == 0)
            finish()
    }

    private fun sendBackPressToDrawer(): Boolean {
        val drawer = getDrawer() ?: return false
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
            return true
        }
        return false
    }

    private fun sendBackPressToFragmentOnTop():Boolean {
        val fragment = fragmentHandler.getCurrentFragment()?:return false
        if (fragment !is BackButtonSupportFragment)
            return false

        return (fragment as BackButtonSupportFragment).onBackPressed()
    }

    protected fun add(fragment: BaseFragment) {
        fragmentHandler.add(fragment)
    }

    protected abstract fun getDrawerToggle(): ActionBarDrawerToggle?

    protected abstract fun getDrawer(): DrawerLayout?
}