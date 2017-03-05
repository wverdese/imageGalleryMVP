package me.wverdese.gallery.data.api

import me.wverdese.gallery.data.json.Photo
import retrofit2.http.GET
import rx.Observable

/**
 * Maps the API call to Kotlin methods.
 * Works with Retrofit.
 */
interface RetrofitApiClient {

    @GET("photos") fun getPhotos(): Observable<List<Photo>>
}