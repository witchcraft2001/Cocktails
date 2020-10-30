package ru.dmdev.cocktails.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.dmdev.cocktails.api.CocktailsApi
import ru.dmdev.cocktails.extensions.toModel
import ru.dmdev.cocktails.models.Cocktail
import ru.dmdev.cocktails.repositories.exceptions.RepositoryDataNotFoundException
import ru.dmdev.cocktails.repositories.models.RepositoryResult
import javax.inject.Inject

class CocktailsRepositoryImpl @Inject constructor(private val apiService: CocktailsApi) : CocktailsRepository {

    override suspend fun getFilteredCocktails(categoryName: String): RepositoryResult<List<Cocktail>> {
        return try {
            val response = withContext(Dispatchers.IO) {
                apiService.getFilteredCocktailsAsync(categoryName)
            }
            when {
                response.drinks.isNotEmpty() -> RepositoryResult.Success(response.drinks.map { it.toModel() })
                else -> RepositoryResult.Error(RepositoryDataNotFoundException("Not found"))
            }
        } catch (ex: Throwable) {
            RepositoryResult.Error(ex)
        }
    }
}