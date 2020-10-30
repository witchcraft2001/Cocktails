package ru.dmdev.cocktails.api.responses

import com.squareup.moshi.Json

data class CocktailListResponse (
    @field:Json(name = "drinks") val drinks: List<CocktailResponse>
)