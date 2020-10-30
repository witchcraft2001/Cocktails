package ru.dmdev.cocktails.repositories

import ru.dmdev.cocktails.models.Cocktail
import ru.dmdev.cocktails.repositories.models.RepositoryResult

interface CocktailsRepository {
    suspend fun getFilteredCocktails(categoryName: String): RepositoryResult<List<Cocktail>>
}