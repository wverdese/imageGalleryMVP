package me.wverdese.gallery.ui.grid

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_grid_item.view.*
import me.wverdese.gallery.R
import me.wverdese.gallery.data.json.Photo

/**
 * An Adapter that takes a list of [Photo] objects as parameter and displays them into a list of android views.
 * It also accept a function to react to click events on single items.
 */
class PhotoListAdapter(
        private val data: List<Photo>,
        private val onPhotoSelected: (Photo) -> Unit
) : RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder>() {

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent!!.context)
        val itemView = inflater.inflate(R.layout.layout_grid_item, parent, false)
        return PhotoViewHolder(itemView, onPhotoSelected)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder?, position: Int) =
            holder!!.bind(data[position])

    class PhotoViewHolder(
            itemView: View,
            private val onPhotoSelected: (Photo) -> Unit) : RecyclerView.ViewHolder(itemView) {

        fun bind(photo: Photo) {
            itemView.item_card.setOnClickListener { onPhotoSelected.invoke(photo) }
            val imageUri = Uri.parse(photo.thumbnailUrl)
            itemView.item_thumbnail.setImageURI(imageUri, photo.thumbnailUrl)
            itemView.item_title.text = photo.title
        }
    }
}