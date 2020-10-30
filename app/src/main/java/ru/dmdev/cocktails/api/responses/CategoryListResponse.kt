package ru.dmdev.cocktails.api.responses

import com.squareup.moshi.Json

data class CategoryListResponse (
    @field:Json(name = "drinks") val categories: List<CategoryResponse>
)