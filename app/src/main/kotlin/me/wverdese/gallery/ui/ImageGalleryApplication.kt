package me.wverdese.gallery.ui

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import me.wverdese.gallery.di.Component
import me.wverdese.gallery.di.DaggerComponent

/**
 * The [Application]. Exposes the Dagger Component.
 */
class ImageGalleryApplication : Application() {

    lateinit var component: Component
        private set

    override fun onCreate() {
        super.onCreate()
        component = DaggerComponent.create()
        Fresco.initialize(this)
    }
}