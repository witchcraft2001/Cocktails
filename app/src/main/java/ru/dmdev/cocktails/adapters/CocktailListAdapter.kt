package ru.dmdev.cocktails.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_cocktail_list_item.view.*
import ru.dmdev.cocktails.R
import ru.dmdev.cocktails.adapters.base.SimpleListAdapter
import ru.dmdev.cocktails.models.Cocktail

class CocktailListAdapter : SimpleListAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        return when (viewType) {
            R.layout.layout_cocktail_list_item -> CategoryViewHolder(inflateByViewType(context, viewType, parent))
            else -> throw IllegalStateException("There is no match with current layoutId")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CategoryViewHolder -> {
                val cocktail = items[position] as Cocktail
                holder.name.text = cocktail.name
                holder.id.text = cocktail.id
                Glide
                    .with(holder.imageThumb)
                    .load(cocktail.thumb)
                    .centerCrop()
                    .into(holder.imageThumb)
            }
            else -> throw IllegalStateException("There is no match with current holder instance")
        }
    }

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val id = view.textId
        val name = view.textName
        val imageThumb = view.imageThumb
    }
}