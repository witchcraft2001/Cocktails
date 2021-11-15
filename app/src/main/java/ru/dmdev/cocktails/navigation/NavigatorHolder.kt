package ru.dmdev.cocktails.navigation

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class NavigatorHolder @Inject constructor(fragmentActivity: FragmentActivity, fm: FragmentManager, containerId: Int) : AppNavigator(fragmentActivity, containerId, fm) {


    fun createActivityIntent(context: Context, screenKey: String, data: Any?): Intent? {
        return when (screenKey) {
            //SomeActivity.className -> Intent(context, SomeActivity::class.java)
            //SomeOtherActivity.className -> Intent(context, SomeOtherActivity::class.java)
            else -> null
        }
    }

    fun createFragment(screenKey: String, data: Any?): Fragment? {
        TODO()
//        return when (screenKey) {
//            NavigationKeys.CONTENT_FRAGMENT -> {
//                if (data is Int) {
//                    return ContentFragment.newInstance(data)
//                } else {
//                    throw IllegalArgumentException("Trying to open ContentFragment without providing current depth")
//                }
//            }
//
//            else -> null
//        }
    }
}