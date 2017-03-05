Image Gallery
=============

Simple implementation of an Image Gallery with a Grid and a Detail Screen, running on minSdk >= 16 (targetSdk 25).
This is meant to be shown as an example of MVP architecture on Android.

Installation/Setup
------------------

Run the project on AndroidStudio (be sure to change update channel to "Canary Channel") or run on command line:

```
./gradlew clean build
adb install ./app/build/outputs/apk/app-debug.apk
```

Dependencies
------------

I'm using [Android Studio v2.3 (Canary)](http://tools.android.com/download/studio/builds/2-3-0) and [Gradle v3.4.1](https://docs.gradle.org/3.4.1/release-notes.html) to build.
All the code was written in [Kotlin v1.1](https://blog.jetbrains.com/kotlin/2017/03/kotlin-1-1). This app also includes [Kotlin Android Extensions](https://kotlinlang.org/docs/tutorials/android-plugin.html) to reduce boilerplate required for doing UI on Android.

Other dependencies:

* Android Support Library v7 (AppCompat, RecyclerView, CardView, Design for Snackbars) v25.2.0
* RxJava v2.1.7
* Dagger v2.10-rc2
* Square's Retrofit v2.1.0
* Square's OkHttp v3.6.0
* Facebook's Fresco v1.1.0