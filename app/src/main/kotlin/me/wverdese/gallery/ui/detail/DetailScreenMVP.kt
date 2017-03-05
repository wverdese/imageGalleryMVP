package me.wverdese.gallery.ui.detail

/**
 * MVP definition of the Detail Screen.
 */
interface DetailScreenMVP {

    interface View {
        val presenter: DetailScreenMVP.Presenter

        fun showItem(item: DetailScreenItem)
        fun clearPhotosCache()
    }

    interface Presenter {
        val view: DetailScreenMVP.View

        fun onViewAttached()
        fun onViewDetached()
    }
}