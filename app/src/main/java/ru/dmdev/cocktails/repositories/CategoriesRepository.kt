package ru.dmdev.cocktails.repositories

import ru.dmdev.cocktails.models.Category
import ru.dmdev.cocktails.repositories.models.RepositoryResult

interface CategoriesRepository {
    suspend fun getCategories() : RepositoryResult<List<Category>>
}