package me.wverdese.gallery.ui.detail

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import me.wverdese.gallery.util.MockitoRxTest
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock

class DetailScreenPresenterTest : MockitoRxTest() {

    @field:Mock lateinit var view: DetailScreenMVP.View
    @field:Mock lateinit var item: DetailScreenItem

    @field:InjectMocks lateinit var presenter: DetailScreenPresenter

    fun verifyNoMoreInteractions() =
            verifyNoMoreInteractions(view, item)

    @Test fun onViewAttachedShowData() {
        presenter.onViewAttached()
        verify(view).showItem(item)
        verifyNoMoreInteractions()
    }

    @Test fun onViewDetachedClearCache() {
        presenter.onViewDetached()
        verify(view).clearPhotosCache()
        verifyNoMoreInteractions()
    }
}