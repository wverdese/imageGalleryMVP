package me.wverdese.gallery.ui.grid

import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import me.wverdese.gallery.data.json.Photo
import me.wverdese.gallery.data.repo.PhotosRepository
import me.wverdese.gallery.ui.detail.DetailScreenItem
import me.wverdese.gallery.util.MockitoRxTest
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import rx.Observable
import kotlin.test.assertEquals

class GridScreenPresenterTest : MockitoRxTest() {

    @field:Mock lateinit var view: GridScreenMVP.View
    @field:Mock lateinit var navigator: GridScreenNavigator
    @field:Mock lateinit var model: PhotosRepository

    @field:InjectMocks lateinit var presenter: GridScreenPresenter

    fun verifyNoMoreInteractions() = verifyNoMoreInteractions(view, navigator, model)

    private fun loadDataSuccess(trigger: () -> Unit) {
        //setup
        val photos = emptyList<Photo>()
        whenever(model.fetchPhotos()).thenReturn(Observable.just(photos))

        trigger.invoke()

        //tests
        verify(view).showLoadingIndicator(true)
        verify(model).fetchPhotos()
        verify(view).showLoadingIndicator(false)
        verify(view).displayData(photos)
        verifyNoMoreInteractions()
    }

    private fun loadDataFailure(trigger: () -> Unit) {
        //setup
        whenever(model.fetchPhotos()).thenReturn(Observable.error(RuntimeException("mock")))

        //trigger
        trigger.invoke()

        //tests
        verify(view).showLoadingIndicator(true)
        verify(model).fetchPhotos()
        verify(view).showLoadingIndicator(false)
        verify(view).showErrorMessage()
        verifyNoMoreInteractions()
    }

    @Test fun onViewAttachedLoadDataSuccess() = loadDataSuccess {
        presenter.onViewAttached()
    }

    @Test fun onViewAttachedLoadDataFailure() = loadDataFailure {
        presenter.onViewAttached()
    }

    @Test fun onReloadDataSuccess() = loadDataSuccess {
        presenter.onReloadButtonPressed()
    }

    @Test fun onReloadDataFailure() = loadDataFailure {
        presenter.onReloadButtonPressed()
    }

    @Test fun onViewDetachedClearCache() {
        presenter.onViewDetached()
        verify(view).clearPhotosCache()
        verifyNoMoreInteractions()
    }

    @Test fun onPhotoSelected() {
        //setup
        val photo = Photo(1, 1, "title", "image", "thumbnail")

        //trigger
        presenter.onPhotoSelected(photo)

        //tests
        val captor = argumentCaptor<DetailScreenItem>()
        verify(navigator).goToDetailScreen(captor.capture())
        assertEquals(captor.firstValue.title, photo.title)
        assertEquals(captor.firstValue.imageUrl, photo.imageUrl)
        verifyNoMoreInteractions()
    }
}