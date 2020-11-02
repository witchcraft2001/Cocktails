package ru.dmdev.cocktails.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.dmdev.cocktails.R
import ru.dmdev.cocktails.adapters.base.SimpleListAdapter
import ru.dmdev.cocktails.models.Category
import kotlinx.android.synthetic.main.layout_category_list_item.view.*
import ru.dmdev.cocktails.adapters.listeners.OnAdapterClickListener

class CategoryListAdapter(private val clickListener: OnAdapterClickListener<Category>) : SimpleListAdapter() {

    var selected: Category? = null

    fun setSelectedCategory(category: Category) {
        selected = category
        this.notifyDataSetChanged()
        clickListener.onClickItem(category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        return when (viewType) {
            R.layout.layout_category_list_item -> CategoryViewHolder(inflateByViewType(context, viewType, parent))
            else -> throw IllegalStateException("There is no match with current layoutId")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CategoryViewHolder -> {
                val category = items[position] as Category
                holder.button.text = category.name
                holder.button.textOff = category.name
                holder.button.textOn = category.name
                holder.button.isChecked = (selected?.name ?: (items.first() as Category).name) == category.name
                holder.button.setOnClickListener { setSelectedCategory(items[position] as Category)}
            }
            else -> throw IllegalStateException("There is no match with current holder instance")
        }
    }

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val button = view.btnCategory
    }
}