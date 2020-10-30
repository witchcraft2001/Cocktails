package ru.dmdev.cocktails.extensions

import ru.dmdev.cocktails.api.responses.CategoryResponse
import ru.dmdev.cocktails.models.Category

fun CategoryResponse.toModel()  = Category( name )