package me.wverdese.gallery.data.repo

import me.wverdese.gallery.data.json.Photo
import rx.Observable

/**
 * Represents repository for [Photo] objects (Model).
 */
interface PhotosRepository {

    fun fetchPhotos(): Observable<List<Photo>>
}