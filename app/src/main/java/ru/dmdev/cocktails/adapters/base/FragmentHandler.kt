package ru.dmdev.cocktails.adapters.base

import android.util.Log
import androidx.fragment.app.FragmentManager
import ru.dmdev.cocktails.R

class FragmentHandler(private final val fragmentManager: FragmentManager) {
    companion object {
        val TAG = "FragmentHandler"
    }

    fun add(fragment: BaseFragment, clearStack: Boolean = false, addToBackStack: Boolean = false) {
        var currentFragment = getCurrentFragment()
        if (currentFragment?.javaClass == fragment.javaClass) {
            Log.w(TAG, "Tried to add a fragment of the same type to the backstack. This may be done on purpose in some circumstances but generally should be avoided.")
            return
        }
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragment_container, fragment, fragment.tag)

        if (addToBackStack)
            fragmentTransaction.addToBackStack(fragment.tag)

        if (clearStack && !addToBackStack && fragmentManager.backStackEntryCount != 0)
            clearBackStack()

        fragmentTransaction.commitAllowingStateLoss()
    }

    fun getCurrentFragment(): BaseFragment? {
        if (fragmentManager.backStackEntryCount == 0)
            return null

        val currentEntry = fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1)

        return fragmentManager.findFragmentByTag(currentEntry.name) as BaseFragment
    }

    fun clearBackStack() {
        try {
            val currentEntry = fragmentManager.getBackStackEntryAt(0)
            fragmentManager.popBackStack(currentEntry.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            fragmentManager.executePendingTransactions()
        }catch (e: RuntimeException) {
            e.printStackTrace()
        }
    }
}