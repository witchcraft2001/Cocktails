package ru.dmdev.cocktails

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import ru.dmdev.cocktails.databinding.ActivityMainBinding
import ru.dmdev.cocktails.screens.search.SearchFragment
import ru.dmdev.cocktails.utils.ActivityUtils
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var searchFragment: SearchFragment

    @Inject
    lateinit var activityUtils: ActivityUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        activityUtils.setCurrentFragment(this, searchFragment, false, null, false)
    }
}