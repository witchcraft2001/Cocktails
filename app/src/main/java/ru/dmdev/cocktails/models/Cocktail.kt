package ru.dmdev.cocktails.models

import android.os.Parcel
import android.os.Parcelable
import ru.dmdev.cocktails.R
import ru.dmdev.cocktails.adapters.base.IBaseListItem

data class Cocktail (
    val name: String,
    val thumb: String?,
    val id: String
): Parcelable, IBaseListItem {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: throw IllegalArgumentException("Argument name is null"),
        parcel.readString(),
        parcel.readString() ?: throw IllegalArgumentException("Argument id is null")
    )

    override fun getLayoutId() = R.layout.layout_cocktail_list_item
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(thumb)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cocktail> {
        override fun createFromParcel(parcel: Parcel): Cocktail {
            return Cocktail(parcel)
        }

        override fun newArray(size: Int): Array<Cocktail?> {
            return arrayOfNulls(size)
        }
    }
}