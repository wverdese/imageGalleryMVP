package me.wverdese.gallery.data.repo

import me.wverdese.gallery.data.api.RetrofitApiClient
import me.wverdese.gallery.data.json.Photo
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implements [PhotosRepository] using an instance of [RetrofitApiClient] to fetch the photos from an API endpoint and return them in an Observable.
 * The APi call is performed at the first subscribe, then the list of photos is cached for future requests.
 * This is meant to be used as a singleton.
 */
@Singleton class SimpleCachePhotosRepository @Inject constructor(
        private val apiClient: RetrofitApiClient
) : PhotosRepository {

    internal var observable: Observable<List<Photo>>? = null

    override fun fetchPhotos(): Observable<List<Photo>> {
        if (observable == null) {
            observable = apiClient.getPhotos().cache()
        }
        return observable!!
    }
}