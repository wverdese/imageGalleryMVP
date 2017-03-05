package me.wverdese.gallery.ui.detail

import android.os.Parcel
import android.os.Parcelable

/**
 * A Parcelable data class to wrap the necessary model infos for the Detail Screen.
 */
open class DetailScreenItem(
        val title: String,
        val imageUrl: String
) : Parcelable {

    constructor(source: Parcel) : this(source.readString(), source.readString())

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(this.title)
        dest?.writeString(this.imageUrl)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<DetailScreenItem> = object : Parcelable.Creator<DetailScreenItem> {

            override fun createFromParcel(source: Parcel): DetailScreenItem {
                return DetailScreenItem(source)
            }

            override fun newArray(size: Int): Array<DetailScreenItem?> {
                return arrayOfNulls(size)
            }
        }
    }
}