package me.wverdese.gallery.ui.grid

import me.wverdese.gallery.data.json.Photo

/**
 * MVP definition of the Grid Screen.
 */
interface GridScreenMVP {

    interface View {

        val presenter: Presenter

        fun showLoadingIndicator(show: Boolean)
        fun displayData(photos: List<Photo>)
        fun showErrorMessage()
        fun clearPhotosCache()
    }

    interface Presenter {

        val view: View

        fun onViewAttached()
        fun onViewDetached()
        fun onReloadButtonPressed()
        fun onPhotoSelected(photo: Photo)
    }
}