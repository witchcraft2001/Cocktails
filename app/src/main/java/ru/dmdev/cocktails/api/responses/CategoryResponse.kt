package ru.dmdev.cocktails.api.responses

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("strCategory") val name: String
)