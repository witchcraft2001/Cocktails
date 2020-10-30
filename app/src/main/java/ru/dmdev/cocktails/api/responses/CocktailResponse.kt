package ru.dmdev.cocktails.api.responses

import com.squareup.moshi.Json

data class CocktailResponse (
    @Json(name="strDrink") val name: String,
    @Json(name="strDrinkThumb") val thumb: String,
    @Json(name="idDrink") val id: String
)