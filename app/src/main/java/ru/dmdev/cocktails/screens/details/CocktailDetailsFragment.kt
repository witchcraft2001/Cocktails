package ru.dmdev.cocktails.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dagger.android.support.DaggerFragment
import ru.dmdev.cocktails.R
import ru.dmdev.cocktails.databinding.FragmentCocktailDetailsBinding
import ru.dmdev.cocktails.models.Cocktail
import javax.inject.Inject

private const val COCKTAIL_PARCEL = "COCKTAIL_PARCEL"

class CocktailDetailsFragment : DaggerFragment() {
    private lateinit var binding: FragmentCocktailDetailsBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel : CocktailDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cocktail_details, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory)[CocktailDetailsViewModel::class.java]
        binding.viewModel = viewModel
        arguments?.let { it ->
            it.getParcelable<Cocktail>(COCKTAIL_PARCEL)?.let { item ->
                viewModel.setCocktailInfo(item)
            }
        }

        viewModel.thumb.observe(viewLifecycleOwner, Observer { url ->
            url?.let {
                Glide
                    .with(requireContext())
                    .load(url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(binding.imgThumb)
            }
        })
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(item: Cocktail) =
            CocktailDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(COCKTAIL_PARCEL, item)
                }
            }
    }
}