package me.wverdese.gallery.ui.detail

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import me.wverdese.gallery.ui.ActivityModule
import me.wverdese.gallery.ui.ActivityScope

/**
 * Dagger SubComponent that allows the Activity to inject itself after creation to create its dependencies.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(DetailScreenModule::class))
interface DetailScreenSubComponent {
    fun inject(activity: DetailScreenActivity)
}

/**
 * Dagger Module for creating the MVP structure of the Detail Screen after the Activity is injected.
 * It requires also a [DetailScreenItem] as parameter.
 */
@Module(includes = arrayOf(DetailScreenModuleBindings::class))
class DetailScreenModule(
        thisActivity: DetailScreenActivity,
        private val item: DetailScreenItem)
    : ActivityModule<DetailScreenActivity>(thisActivity) {

    @Provides fun presenter(): DetailScreenMVP.Presenter =
            DetailScreenPresenter(thisActivity, item)
}

/**
 * Dagger Module interface that binds the interfaces to their corresponding "dependency-free" implementation, reducing boilerplate.
 */
@Module abstract class DetailScreenModuleBindings {
    @Binds abstract fun view(it: DetailScreenActivity): DetailScreenMVP.View
}