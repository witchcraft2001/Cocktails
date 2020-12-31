package ru.dmdev.cocktails.adapters.base

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {
    private lateinit var fragmentHandler: FragmentHandler

    protected abstract val title: String

    protected val subTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        fragmentHandler = FragmentHandler(activity!!.supportFragmentManager)
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        activity?.title = title
    }

    protected fun add(fragment: BaseFragment) {
        fragmentHandler.add(fragment)
    }
}