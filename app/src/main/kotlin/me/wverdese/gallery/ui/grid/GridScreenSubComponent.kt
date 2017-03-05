package me.wverdese.gallery.ui.grid

import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import me.wverdese.gallery.ui.ActivityModule
import me.wverdese.gallery.ui.ActivityScope

/**
 * Dagger SubComponent that allows the Activity to inject itself after creation to create its dependencies.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(GridScreenModule::class))
interface GridScreenSubComponent {
    fun inject(activity: GridScreenActivity)
}

/**
 * Dagger Module for creating the MVP structure of the Grid Screen after the Activity is injected.
 */
@Module(includes = arrayOf(GridScreenModuleBindings::class))
class GridScreenModule(thisActivity: GridScreenActivity)
    : ActivityModule<GridScreenActivity>(thisActivity)

/**
 * Dagger Module interface that binds the interfaces to their corresponding "dependency-free" implementation, reducing boilerplate.
 */
@Module abstract class GridScreenModuleBindings {
    @Binds abstract fun navigator(it: GridScreenActivity): GridScreenNavigator
    @Binds abstract fun view(it: GridScreenActivity): GridScreenMVP.View
    @Binds abstract fun presenter(it: GridScreenPresenter): GridScreenMVP.Presenter
}