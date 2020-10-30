package ru.dmdev.cocktails.di.modules

import dagger.Module
import dagger.Provides
import ru.dmdev.cocktails.api.CocktailsApi
import ru.dmdev.cocktails.repositories.CategoriesRepository
import ru.dmdev.cocktails.repositories.CategoriesRepositoryImpl
import ru.dmdev.cocktails.repositories.CocktailsRepository
import ru.dmdev.cocktails.repositories.CocktailsRepositoryImpl
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideCocktailsRepository(cocktailsApi: CocktailsApi): CocktailsRepository {
        return CocktailsRepositoryImpl(cocktailsApi)
    }

    @Provides
    @Singleton
    fun provideCategoriesRepository(cocktailsApi: CocktailsApi): CategoriesRepository {
        return CategoriesRepositoryImpl(cocktailsApi)
    }
}