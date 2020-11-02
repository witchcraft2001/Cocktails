package ru.dmdev.cocktails.adapters.base

interface IBaseListAdapter <T> {
    fun add(newItem: T)
    fun add(newItems: List<T>?)
    fun addAtPosition(position: Int, newItem: T)
    fun getItemAtPosition(position: Int) : T?
    fun remove(position: Int)
    fun clearAll()
}