package ru.dmdev.cocktails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.dmdev.cocktails.adapters.CategoryListAdapter
import ru.dmdev.cocktails.adapters.CocktailListAdapter
import ru.dmdev.cocktails.databinding.ActivityMainBinding
import ru.dmdev.cocktails.models.Category
import ru.dmdev.cocktails.models.Cocktail
import ru.dmdev.cocktails.viewmodels.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var categoriesAdapter: CategoryListAdapter
    private lateinit var cocktailsAdapter: CocktailListAdapter
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as CocktailsApp).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initCategoriesRecyclerView(binding.rvCategories)
        initRecyclerView(binding.rvItems)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.refreshCategories()

        viewModel.categories.observe(this, Observer { categories ->
            categories?.let {
                showCategories(it)
            }
        })

        viewModel.cocktails.observe(this, Observer { categories ->
            categories?.let {
                showCocktails(it)
            }
        })
    }

    private fun showCategories(list: List<Category>) {
        categoriesAdapter.clearAll()
        categoriesAdapter.add(list)
    }

    private fun showCocktails(list: List<Cocktail>) {
        cocktailsAdapter.clearAll()
        cocktailsAdapter.add(list)
    }

    private fun initCategoriesRecyclerView(rvItem: RecyclerView) {
        categoriesAdapter = CategoryListAdapter()
        rvItem.adapter = categoriesAdapter
        rvItem.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvItem.setHasFixedSize(false)
    }

    private fun initRecyclerView(rvItem: RecyclerView) {
        cocktailsAdapter = CocktailListAdapter()
        rvItem.adapter = cocktailsAdapter
        rvItem.layoutManager = GridLayoutManager(this, 2)
        rvItem.setHasFixedSize(true)
    }
}