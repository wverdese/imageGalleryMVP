package me.wverdese.gallery.ui.detail

import javax.inject.Inject

/**
 * The Presenter implementation of the Detail screen.
 * Contains the business logic necessary to react to view events and display the [DetailScreenItem] passed as parameter.
 */
class DetailScreenPresenter @Inject constructor(
        override val view: DetailScreenMVP.View,
        private val item: DetailScreenItem
) : DetailScreenMVP.Presenter {

    override fun onViewAttached() = view.showItem(item)

    override fun onViewDetached() = view.clearPhotosCache()
}