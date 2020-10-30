package ru.dmdev.cocktails.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.dmdev.cocktails.models.Category
import ru.dmdev.cocktails.repositories.CategoriesRepository
import ru.dmdev.cocktails.repositories.models.RepositoryResult
import javax.inject.Inject

class MainViewModel @Inject constructor(private val categoriesRepository: CategoriesRepository) : ViewModel() {
    private val _spinner = MutableLiveData<Boolean>()
    val spinner : LiveData<Boolean> get() = _spinner

    private val _categories = MutableLiveData<List<Category>>()
    val categories : LiveData<List<Category>> get() = _categories

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun refreshCategories() {
        if (_categories.value?.isEmpty() == false)
            return

        viewModelScope.launch {
            _spinner.postValue(true)
            when (val result = categoriesRepository.getCategories()) {
                is RepositoryResult.Success -> _categories.postValue(result.data)
                is RepositoryResult.Error -> _error.postValue(result.exception.message)
            }
            _spinner.postValue(false)
        }
    }
}