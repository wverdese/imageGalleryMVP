package me.wverdese.gallery.ui.grid

import me.wverdese.gallery.ui.detail.DetailScreenItem

/**
 * Provides methods to navigate from Grid Screen.
 */
interface GridScreenNavigator {

    fun goToDetailScreen(item: DetailScreenItem)
}