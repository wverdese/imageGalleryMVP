package me.wverdese.gallery.ui

import android.app.Activity
import dagger.Module
import dagger.Provides
import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.SOURCE

@Scope @MustBeDocumented @Retention(SOURCE) annotation class ActivityScope

/**
 * A Dagger Module that wraps the activity passed as parameter and returns it as a dependency.
 * The annotation class [ActivityScope] allows to have a different instance of Module per Activity.
 */
@Module abstract class ActivityModule<out T : Activity>(@get:[Provides ActivityScope] val thisActivity: T) {
    @get:[Provides ActivityScope] val activity: Activity = thisActivity
}