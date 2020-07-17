package com.rxjavasample.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private var retrofit: APIs? = null
    private val TIMEOUT_CONNECT = 90
    private val TIMEOUT_READ = 90

    val client: APIs
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(Api.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.createWithScheduler(
                            Schedulers.io()
                        )
                    )
                    .build().create(APIs::class.java)
            }
            return retrofit!!
        }

    private val okHttpClient: OkHttpClient
        get() {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val builder = OkHttpClient.Builder()
            builder.interceptors().add(logging)
            builder.interceptors().add(interceptor)
            builder.connectTimeout(TIMEOUT_CONNECT.toLong(), TimeUnit.SECONDS)
            builder.readTimeout(TIMEOUT_READ.toLong(), TimeUnit.SECONDS)
            return builder.build()

        }

    private val interceptor: Interceptor
        get() = Interceptor { chain ->
            val original = chain.request()
            val request: Request
            request = original.newBuilder().build()
            chain.proceed(request)
        }
}