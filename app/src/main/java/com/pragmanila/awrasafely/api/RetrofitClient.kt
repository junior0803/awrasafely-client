package com.pragmanila.awrasafely.api

import com.pragmanila.awrasafely.R
import com.pragmanila.awrasafely.api.model.Api
import com.pragmanila.awrasafely.utils.Constants.Companion.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    companion object {


        fun getRetroInstance(): Retrofit {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(MyInterceptor())
            client.addInterceptor(logging)

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}

//object RetrofitClient {
//
//    private val client=OkHttpClient.Builder().apply {
//        addInterceptor(MyInterceptor())
//    }.build()
//
//    private val retrofit by lazy{
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//    val api:ApiInterface by lazy{
//        retrofit.create(ApiInterface::class.java)
//    }
//}
