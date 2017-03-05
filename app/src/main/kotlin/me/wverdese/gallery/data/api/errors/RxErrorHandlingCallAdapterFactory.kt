package me.wverdese.gallery.data.api.errors

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import rx.Observable
import rx.schedulers.Schedulers
import java.lang.reflect.Type

/**
 * Allows Retrofit method to return [Observable], for the combined use of Retrofit and RxJava.
 * All the exceptions raised from the API call are wrapped in an [ApiException] and can be observed as errors.
 */
class RxErrorHandlingCallAdapterFactory private constructor() : CallAdapter.Factory() {
    companion object {
        fun create(): CallAdapter.Factory = RxErrorHandlingCallAdapterFactory()
    }

    private val original: RxJavaCallAdapterFactory = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io())

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*> =
            RxCallAdapterWrapper(original.get(returnType, annotations, retrofit))

    private class RxCallAdapterWrapper internal constructor(private val wrapped: CallAdapter<*>) : CallAdapter<Observable<*>> {
        override fun responseType(): Type = wrapped.responseType()

        override fun <R> adapt(call: Call<R>): Observable<*> = (wrapped.adapt(call) as Observable<*>)
                .onErrorResumeNext {
                    Observable.error(it.asApiException())
                }
    }
}
