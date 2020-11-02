package ru.dmdev.cocktails.adapters.listeners

interface OnAdapterClickListener<T> {
    fun onClickItem(item: T)
}