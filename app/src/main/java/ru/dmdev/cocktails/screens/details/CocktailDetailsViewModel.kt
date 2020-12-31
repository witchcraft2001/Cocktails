package ru.dmdev.cocktails.screens.details

import androidx.lifecycle.ViewModel
import ru.dmdev.cocktails.repositories.CocktailsRepository
import javax.inject.Inject

class CocktailDetailsViewModel @Inject constructor(private val cocktailsRepository: CocktailsRepository) : ViewModel(){

}