package ru.dmdev.cocktails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import ru.dmdev.cocktails.viewmodels.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as CocktailsApp).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }
}