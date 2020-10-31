package ru.dmdev.cocktails.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.dmdev.cocktails.models.Category
import ru.dmdev.cocktails.models.Cocktail
import ru.dmdev.cocktails.repositories.CategoriesRepository
import ru.dmdev.cocktails.repositories.CocktailsRepository
import ru.dmdev.cocktails.repositories.models.RepositoryResult
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
    private val cocktailsRepository: CocktailsRepository) : ViewModel() {
    private val _spinner = MutableLiveData<Boolean>()
    val spinner : LiveData<Boolean> get() = _spinner

    private val _categories = MutableLiveData<List<Category>>()
    val categories : LiveData<List<Category>> get() = _categories

    private val _cocktails = MutableLiveData<List<Cocktail>>()
    val cocktails : LiveData<List<Cocktail>> get() = _cocktails

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun refreshCategories() {
        if (_categories.value?.isEmpty() == false)
            return

        viewModelScope.launch {
            _spinner.postValue(true)
            when (val result = categoriesRepository.getCategories()) {
                is RepositoryResult.Success -> {
                    _categories.postValue(result.data)
                    if (result.data.isNotEmpty())
                        getCocktails(result.data.first().name)
                }
                is RepositoryResult.Error -> _error.postValue(result.exception.message)
            }
            _spinner.postValue(false)
        }
    }

    fun getCocktails(category: String) {
        if (category.isEmpty())
            return

        viewModelScope.launch {
            _spinner.postValue(true)
            when (val result = cocktailsRepository.getFilteredCocktails(category)) {
                is RepositoryResult.Success -> _cocktails.postValue(result.data)
                is RepositoryResult.Error -> _error.postValue(result.exception.message)
            }
            _spinner.postValue(false)
        }
    }
}