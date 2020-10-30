package ru.dmdev.cocktails.api.responses

import com.google.gson.annotations.SerializedName

data class CategoryListResponse (
    @SerializedName("drinks") val categories: List<CategoryResponse>
)