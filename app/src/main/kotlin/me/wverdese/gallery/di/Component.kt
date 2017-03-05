package me.wverdese.gallery.di

import dagger.Component
import me.wverdese.gallery.data.api.ApiModule
import me.wverdese.gallery.data.repo.PhotosRepositoryModule
import me.wverdese.gallery.ui.detail.DetailScreenModule
import me.wverdese.gallery.ui.detail.DetailScreenSubComponent
import me.wverdese.gallery.ui.grid.GridScreenModule
import me.wverdese.gallery.ui.grid.GridScreenSubComponent
import javax.inject.Singleton

/**
 * The main Dagger Component of the app. Sets up the MVP architecture.
 */
@Singleton
@Component(modules = arrayOf(ApiModule::class, PhotosRepositoryModule::class))
interface Component {

    fun gridScreenSubComponent(module: GridScreenModule): GridScreenSubComponent
    fun detailScreenSubComponent(module: DetailScreenModule): DetailScreenSubComponent
}