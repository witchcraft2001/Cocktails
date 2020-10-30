package ru.dmdev.cocktails.extensions

import ru.dmdev.cocktails.api.responses.CocktailResponse
import ru.dmdev.cocktails.models.Cocktail

fun CocktailResponse.toModel()  = Cocktail( name, thumb, id )