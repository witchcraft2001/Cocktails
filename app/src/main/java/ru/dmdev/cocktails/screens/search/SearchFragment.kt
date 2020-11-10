package ru.dmdev.cocktails.screens.search

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import dagger.android.support.DaggerFragment
import ru.dmdev.cocktails.R
import ru.dmdev.cocktails.adapters.CategoryListAdapter
import ru.dmdev.cocktails.adapters.CocktailListAdapter
import ru.dmdev.cocktails.adapters.listeners.OnAdapterClickListener
import ru.dmdev.cocktails.databinding.ActivityMainBinding
import ru.dmdev.cocktails.databinding.FragmentSearchBinding
import ru.dmdev.cocktails.models.Category
import ru.dmdev.cocktails.models.Cocktail
import javax.inject.Inject


class SearchFragment : DaggerFragment {
    private lateinit var categoriesAdapter: CategoryListAdapter
    private lateinit var cocktailsAdapter: CocktailListAdapter
    private lateinit var binding: FragmentSearchBinding


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var viewModel : SearchViewModel

    @Inject
    constructor() : super()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        initCategoriesRecyclerView(binding.rvCategories)
        initRecyclerView(binding.rvItems)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        viewModel = ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]

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

    private fun updateCocktailList(category: Category) {
        viewModel.getCocktails(category.name)
    }

    private fun initCategoriesRecyclerView(rvItem: RecyclerView) {
        categoriesAdapter = CategoryListAdapter(object: OnAdapterClickListener<Category> {
            override fun onClickItem(item: Category) {
                updateCocktailList(item)
            }
        })
        rvItem.adapter = categoriesAdapter
        rvItem.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvItem.setHasFixedSize(false)
    }

    private fun initRecyclerView(rvItem: RecyclerView) {
        cocktailsAdapter = CocktailListAdapter()
        rvItem.adapter = cocktailsAdapter
        rvItem.layoutManager = GridLayoutManager(context, 2)
        rvItem.setHasFixedSize(true)
    }
}