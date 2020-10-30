package ru.dmdev.cocktails.api.responses

import com.google.gson.annotations.SerializedName

data class CocktailResponse (
    @SerializedName("strDrink") val name: String,
    @SerializedName("strDrinkThumb") val thumb: String,
    @SerializedName("idDrink") val id: String
)