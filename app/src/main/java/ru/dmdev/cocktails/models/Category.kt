package ru.dmdev.cocktails.models

import ru.dmdev.cocktails.R
import ru.dmdev.cocktails.adapters.base.IBaseListItem

data class Category(
    val name: String
): IBaseListItem {
    override fun getLayoutId() = R.layout.layout_item
}