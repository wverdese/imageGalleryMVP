package me.wverdese.gallery.data.api

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import me.wverdese.gallery.data.api.errors.RxErrorHandlingCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier annotation class ApiEndpoint

/**
 * Dagger Module to create all the dependencies to call th APIs using Retrofit.
 * The [ApiEndpoint] annotated String returned by the function [apiEndpoint] contains the APIs host's URL.
 */
@Module class ApiModule {

    @Singleton @Provides @ApiEndpoint internal fun apiEndpoint() =
            "https://jsonplaceholder.typicode.com"

    @Provides internal fun okHttpClient() =
            OkHttpClient
                    .Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    })
                    .build()

    @Provides internal fun gson() = Gson()

    @Provides internal fun retrofit(
            @ApiEndpoint host: String,
            gson: Gson,
            okHttpClient: OkHttpClient
    ) = Retrofit.Builder()
            .baseUrl(host)
            .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides fun retrofitApiClient(
            retrofit: Retrofit
    ) = retrofit.create(RetrofitApiClient::class.java)
}