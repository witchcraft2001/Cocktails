package ru.dmdev.cocktails.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.dmdev.cocktails.models.Cocktail
import ru.dmdev.cocktails.repositories.CocktailsRepository
import javax.inject.Inject

class CocktailDetailsViewModel @Inject constructor(private val cocktailsRepository: CocktailsRepository) : ViewModel(){

    var cocktail: Cocktail? = null

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    private val _id = MutableLiveData<String>()
    val id: LiveData<String> get() = _id

    private val _thumb = MutableLiveData<String?>()
    val thumb: LiveData<String?> get() = _thumb

    fun setCocktailInfo(item: Cocktail) {
        _title.value = item.name
        _id.value = item.id
        _thumb.value = item.thumb
    }
}