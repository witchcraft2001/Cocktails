package ru.dmdev.cocktails.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import ru.dmdev.cocktails.R
import ru.dmdev.cocktails.screens.search.SearchViewModel
import javax.inject.Inject

private const val COCKTAIL_ID = "cocktail_id"

class CocktailDetailsFragment : DaggerFragment() {
    private var cocktailId: String? = null
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var viewModel : CocktailDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cocktailId = it.getString(COCKTAIL_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cocktail_details, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(id: String) =
            CocktailDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(COCKTAIL_ID, id)
                }
            }
    }
}