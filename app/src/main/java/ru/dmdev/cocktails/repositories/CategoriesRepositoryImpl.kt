package ru.dmdev.cocktails.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.dmdev.cocktails.api.CocktailsApi
import ru.dmdev.cocktails.extensions.toModel
import ru.dmdev.cocktails.models.Category
import ru.dmdev.cocktails.repositories.exceptions.RepositoryDataNotFoundException
import ru.dmdev.cocktails.repositories.models.RepositoryResult
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(private val apiService: CocktailsApi) : CategoriesRepository {

    override suspend fun getCategories(): RepositoryResult<List<Category>> {
        return try {
            val response = withContext(Dispatchers.IO) {
                apiService.getCategoriesAsync();
            }
            when {
                response.categories.isNotEmpty() -> RepositoryResult.Success(response.categories.map { it.toModel() })
                else -> RepositoryResult.Error(RepositoryDataNotFoundException("Not found"))
            }
        } catch (ex: Throwable) {
            RepositoryResult.Error(ex)
        }
    }
}