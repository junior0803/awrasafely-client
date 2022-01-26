package com.pragmanila.awrasafely.api

import com.pragmanila.awrasafely.api.model.Api
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit



//
//object RetrofitClientPragger {
//
//
//    private val okHttpClient = OkHttpClient.Builder()
//        .addInterceptor { chain ->
//            val original = chain.request()
//            val requestBuilder = original.newBuilder()
//                .method(original.method(), original.body())
//
//            val request = requestBuilder.build()
//            chain.proceed(request)
//        }.build()
//
//
//    val instance: Api by lazy {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("http://api-dev.awrasafely.prgmnl.com")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
//            .build()
//
//        retrofit.create(Api::class.java)
//    }
//}