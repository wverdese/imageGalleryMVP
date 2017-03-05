package me.wverdese.gallery.ui.grid

import android.util.Log
import me.wverdese.gallery.data.json.Photo
import me.wverdese.gallery.data.repo.PhotosRepository
import me.wverdese.gallery.ui.detail.DetailScreenItem
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * The Presenter implementation of the Grid screen.
 * Contains the business logic to react to view events fetching the data from the model and organizing it into data sent to the View to be displayed (although in this simple scenario the mapping is 1:1).
 * It also reacts to navigation events by interacting with the Navigator.
 */
class GridScreenPresenter @Inject constructor(
        override val view: GridScreenMVP.View,
        private val navigator: GridScreenNavigator,
        private val api: PhotosRepository
) : GridScreenMVP.Presenter {

    private val TAG = this::class.java.name

    private var apiSubscription: Subscription? = null

    override fun onViewAttached() = performLoadData()

    override fun onViewDetached() {
        unsubscribeToApiCall()
        view.clearPhotosCache()
    }

    override fun onPhotoSelected(photo: Photo) {
        val detailItem = DetailScreenItem(photo.title, photo.imageUrl)
        navigator.goToDetailScreen(detailItem)
    }

    override fun onReloadButtonPressed() = performLoadData()

    private fun performLoadData() {
        view.showLoadingIndicator(true)
        subscribeToApiCall()
    }

    private fun onApiCallSuccess(photos: List<Photo>) {
        view.showLoadingIndicator(false)
        view.displayData(photos)
    }

    private fun onApiCallFailure(error: Throwable): Unit {
        Log.e(TAG, "error on api call", error)
        view.showLoadingIndicator(false)
        view.showErrorMessage()
    }

    private fun subscribeToApiCall() {
        unsubscribeToApiCall()
        apiSubscription = api
                .fetchPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { onApiCallSuccess(it) },
                        { onApiCallFailure(it) }
                )
    }

    private fun unsubscribeToApiCall() {
        apiSubscription?.apply {
            if (!isUnsubscribed) unsubscribe()
        }
        apiSubscription = null
    }
}