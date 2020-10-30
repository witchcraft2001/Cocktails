package ru.dmdev.cocktails.api.responses

import com.squareup.moshi.Json

data class CategoryResponse(
    @Json(name="strCategory") val name: String
)