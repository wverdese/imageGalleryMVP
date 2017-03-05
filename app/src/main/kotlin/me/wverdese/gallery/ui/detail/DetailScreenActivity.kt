package me.wverdese.gallery.ui.detail

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_detail_screen.*
import me.wverdese.gallery.R
import me.wverdese.gallery.ui.ImageGalleryApplication
import javax.inject.Inject

/**
 * Activity that implements the Detail Screen View.
 * Accepts a [DetailScreenItem] as an Intent parameter and gives it to the Presenter during its creation.
 */
class DetailScreenActivity : AppCompatActivity(), DetailScreenMVP.View {

    companion object {
        const val EXTRA_DETAIL_ITEM = "detail_item"
    }

    @Inject override lateinit var presenter: DetailScreenMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_screen)

        val item: DetailScreenItem = intent.getParcelableExtra(EXTRA_DETAIL_ITEM)

        (application as ImageGalleryApplication)
                .component
                .detailScreenSubComponent(DetailScreenModule(this, item))
                .inject(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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

    override fun showItem(item: DetailScreenItem) {
        supportActionBar?.title = item.title
        val imageUri = Uri.parse(item.imageUrl)
        detail_image.setImageURI(imageUri, item.imageUrl)
    }


    override fun clearPhotosCache() {
        Fresco.getImagePipeline().clearMemoryCaches()
    }
}