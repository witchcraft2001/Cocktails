package ru.dmdev.cocktails.adapters.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class SimpleListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), IBaseListAdapter<IBaseListItem> {
    protected val items: ArrayList<IBaseListItem> = ArrayList()

    override fun getItemCount() = items.size
    override fun getItemViewType(position: Int) = items[position].getLayoutId()

    protected fun inflateByViewType(context: Context?, viewType: Int, parent: ViewGroup) =
        LayoutInflater.from(context).inflate(viewType, parent, false)

    override fun add(newItem: IBaseListItem) {
        items.add(newItem)
        notifyDataSetChanged()
    }

    override fun getItemAtPosition(position: Int): IBaseListItem? {
        if (items.size >= position)
            return null

        return items[position]
    }

    override fun add(newItems: List<IBaseListItem>?) {
        items.addAll(newItems?.toList() ?: return)
        notifyDataSetChanged()
    }

    override fun addAtPosition(position: Int, newItem: IBaseListItem) {
        items.add(position, newItem)
        notifyItemInserted(position)
    }

    override fun clearAll() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun remove(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }
}