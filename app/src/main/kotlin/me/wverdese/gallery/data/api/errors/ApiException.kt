package me.wverdese.gallery.data.api.errors

import retrofit2.Response
import retrofit2.adapter.rxjava.HttpException
import java.io.IOException

/**
 * Sealed class that extends [RuntimeException] defines special subtypes of error scenarios that may occur when calling an API endpoint.
 */
sealed class ApiException(message: String? = null, exception: Throwable? = null) : RuntimeException(message, exception) {

    /**
     * Raised when a http code != 20* has returned.
     * Wraps the called URL and the response returned from server (code + message).
     */
    data class Http(val url: String?, val response: Response<*>?)
        : ApiException(message = "${response?.code()} ${response?.message()}")

    /**
     * Raised when the request goes in timeout.
     * Wraps the corresponding [IOException].
     */
    class Network(exception: IOException) : ApiException(exception = exception)

    /**
     * Wraps an unexpected exception.
     */
    class Unexpected(exception: Throwable) : ApiException(exception = exception)
}

/**
 * Extension function for [Throwable].
 * Maps the exception to the corresponding [ApiException].
 */
fun Throwable.asApiException(): ApiException = when (this) {
    is ApiException -> {
        // Already wrapped
        this
    }
    is HttpException -> {
        // We had non-20* http error
        val response = response()
        ApiException.Http(response?.raw()?.request()?.url().toString(), response)
    }
    is IOException -> {
        // A network error happened
        ApiException.Network(this)
    }
    else -> {
        // We don't know what happened. We need to simply convert to an unknown error
        ApiException.Unexpected(this)
    }
}
