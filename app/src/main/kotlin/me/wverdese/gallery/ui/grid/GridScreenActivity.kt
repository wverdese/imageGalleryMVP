package me.wverdese.gallery.ui.grid

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_grid_screen.*
import me.wverdese.gallery.R
import me.wverdese.gallery.data.json.Photo
import me.wverdese.gallery.ui.ImageGalleryApplication
import me.wverdese.gallery.ui.detail.DetailScreenActivity
import me.wverdese.gallery.ui.detail.DetailScreenItem
import me.wverdese.gallery.ui.utils.GridSpacingItemDecoration
import javax.inject.Inject


/**
 * Activity that implements the Grid Screen View and Navigator.
 * Wraps a [RecyclerView] that uses a [GridLayoutManager] and a [PhotoListAdapter] to show the list of [Photo] items inside a grid.
 */
class GridScreenActivity : AppCompatActivity(), GridScreenMVP.View, GridScreenNavigator {

    @Inject override lateinit var presenter: GridScreenMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_screen)

        (application as ImageGalleryApplication)
                .component
                .gridScreenSubComponent(GridScreenModule(this))
                .inject(this)

        recycler_view.apply {
            val columnCount = resources.getInteger(R.integer.column_count)
            layoutManager = GridLayoutManager(this@GridScreenActivity, columnCount, GridLayoutManager.VERTICAL, false)
            addItemDecoration(GridSpacingItemDecoration(columnCount, resources.getDimensionPixelSize(R.dimen.grid_spacing)))
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.onViewAttached()
    }

    override fun onStop() {
        presenter.onViewDetached()
        super.onStop()
    }

    /** MVP.View **/

    override fun showLoadingIndicator(show: Boolean) {
        progress_bar.visibility = if (show) View.VISIBLE else View.GONE
        recycler_view.visibility = if (show) View.GONE else View.VISIBLE
    }

    override fun displayData(photos: List<Photo>) {
        recycler_view.adapter = PhotoListAdapter(photos, presenter::onPhotoSelected)
    }

    override fun showErrorMessage() {
        Snackbar
                .make(coordinator_layout, R.string.error_message, Snackbar.LENGTH_INDEFINITE)
                .apply {
                    setAction(R.string.error_action) {
                        presenter.onReloadButtonPressed()
                        dismiss()
                    }
                }
                .show()
    }

    override fun clearPhotosCache() {
        Fresco.getImagePipeline().clearMemoryCaches()
    }

    /** Navigator **/

    override fun goToDetailScreen(item: DetailScreenItem) {
        Intent(this, DetailScreenActivity::class.java).let {
            it.putExtra(DetailScreenActivity.EXTRA_DETAIL_ITEM, item)
            startActivity(it)
        }
    }
}