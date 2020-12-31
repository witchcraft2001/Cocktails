package ru.dmdev.cocktails.models

import ru.dmdev.cocktails.R
import ru.dmdev.cocktails.adapters.base.IBaseListItem

data class Cocktail (
    val name: String,
    val thumb: String?,
    val id: String
): IBaseListItem {
    override fun getLayoutId() = R.layout.layout_cocktail_list_item
}