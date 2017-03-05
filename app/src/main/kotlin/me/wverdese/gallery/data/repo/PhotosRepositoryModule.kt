package me.wverdese.gallery.data.repo

import dagger.Binds
import dagger.Module

/**
 * Dagger Module to create an instance of PhotosRepository.
 */
@Module abstract class PhotosRepositoryModule {
    @Binds abstract fun photosRepository(it: SimpleCachePhotosRepository): PhotosRepository
}