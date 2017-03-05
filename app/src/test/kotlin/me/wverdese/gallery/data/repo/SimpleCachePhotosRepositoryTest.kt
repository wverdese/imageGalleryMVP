package me.wverdese.gallery.data.repo

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import me.wverdese.gallery.data.api.RetrofitApiClient
import me.wverdese.gallery.data.json.Photo
import me.wverdese.gallery.util.MockitoRxTest
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import rx.Observable
import kotlin.test.assertEquals

class SimpleCachePhotosRepositoryTest : MockitoRxTest() {

    @field:Mock lateinit var apiClient: RetrofitApiClient

    @field:InjectMocks lateinit var repo: SimpleCachePhotosRepository

    fun verifyNoMoreInteractions() = verifyNoMoreInteractions(apiClient)

    @Test fun onFetchPhotosFirstTimeCallApi() {
        //setup
        repo.observable = null
        @Suppress("UNCHECKED_CAST")
        val observable = Mockito.mock(Observable::class.java) as Observable<List<Photo>>
        whenever(apiClient.getPhotos()).thenReturn(observable)

        //trigger
        val returned = repo.fetchPhotos()

        //test
        verify(apiClient).getPhotos()
        assertEquals(repo.observable, returned)
        verifyNoMoreInteractions()
    }

    @Test fun onFetchPhotosReturnCachedPhotosIfAvailable() {
        //setup
        @Suppress("UNCHECKED_CAST")
        repo.observable = Mockito.mock(Observable::class.java) as Observable<List<Photo>>

        //trigger
        val returned = repo.fetchPhotos()

        //test
        assertEquals(repo.observable, returned)
        verifyNoMoreInteractions()
    }
}