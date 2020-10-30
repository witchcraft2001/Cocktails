package ru.dmdev.cocktails.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.dmdev.cocktails.R
import ru.dmdev.cocktails.adapters.base.SimpleListAdapter
import ru.dmdev.cocktails.models.Category
import kotlinx.android.synthetic.main.layout_item.view.*

class CategoryListAdapter : SimpleListAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        return when (viewType) {
            R.layout.layout_item -> CategoryViewHolder(inflateByViewType(context, viewType, parent))
            else -> throw IllegalStateException("There is no match with current layoutId")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CategoryViewHolder -> {
                val countryItem = items[position] as Category
                holder.name.text = countryItem.name
            }
            else -> throw IllegalStateException("There is no match with current holder instance")
        }
    }

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.textName
    }
}