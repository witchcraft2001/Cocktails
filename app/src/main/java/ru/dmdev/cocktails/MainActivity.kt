package ru.dmdev.cocktails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.dmdev.cocktails.adapters.CategoryListAdapter
import ru.dmdev.cocktails.databinding.ActivityMainBinding
import ru.dmdev.cocktails.models.Category
import ru.dmdev.cocktails.viewmodels.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: CategoryListAdapter
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as CocktailsApp).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initRecyclerView(binding.rvItems)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.refreshCategories()

        viewModel.categories.observe(this, Observer { categories ->
            categories?.let {
                showList(it)
            }
        })
    }

    fun showList(countries: List<Category>) {
        adapter.add(countries)
    }

    fun initRecyclerView(rvItem: RecyclerView) {
        adapter = CategoryListAdapter()
        rvItem.adapter = adapter
        rvItem.layoutManager = LinearLayoutManager(this)
        rvItem.setHasFixedSize(true)
    }
}