package ru.dmdev.cocktails.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.dmdev.cocktails.CocktailsApp
import ru.dmdev.cocktails.R
import ru.dmdev.cocktails.adapters.CategoryListAdapter
import ru.dmdev.cocktails.adapters.CocktailListAdapter
import ru.dmdev.cocktails.adapters.listeners.OnAdapterClickListener
import ru.dmdev.cocktails.databinding.FragmentCocktailsBinding
import ru.dmdev.cocktails.interfaces.BackButtonListener
import ru.dmdev.cocktails.models.Category
import ru.dmdev.cocktails.models.Cocktail
import ru.dmdev.cocktails.viewmodels.CocktailsViewModel
import ru.dmdev.cocktails.navigation.NavigationKeys
import javax.inject.Inject

class CocktailsFragment : BaseFragment(), BackButtonListener {
    private lateinit var categoriesAdapter: CategoryListAdapter
    private lateinit var cocktailsAdapter: CocktailListAdapter
    private lateinit var binding: FragmentCocktailsBinding

    override val navigationKey: String = NavigationKeys.LIST_FRAGMENT

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var viewModel : CocktailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        CocktailsApp.instance.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        CocktailsApp.instance.appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[CocktailsViewModel::class.java]
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cocktails, container, false)
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
        return binding.root
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

    override fun onBackPressed(): Boolean {
        return true;
//        return if(isAdded) {
//            val childFragment = childFragmentManager.findFragmentById(R.id.sceneContainer)
//            childFragment != null && childFragment is BackButtonListener && childFragment.onBackPressed()
//        } else false
    }
}