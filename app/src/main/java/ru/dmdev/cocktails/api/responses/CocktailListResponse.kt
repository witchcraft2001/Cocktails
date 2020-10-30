package ru.dmdev.cocktails.api.responses

import com.google.gson.annotations.SerializedName

data class CocktailListResponse (
    @SerializedName("drinks") val drinks: List<CocktailResponse>
)