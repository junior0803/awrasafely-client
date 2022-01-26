package com.pragmanila.awrasafely.api

import okhttp3.Interceptor
import okhttp3.Response


class MyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request=chain.request()
            .newBuilder()
            .addHeader("Content-Type","application/json")
            .addHeader("x-client-id", "MGMwOWVjZDAtYmNmNy00MmQ5LWFiOTctZTc1YjI3NmFhMWMw")
            .addHeader("x-client-secret", "ZjNjZjQyMWEtZTc1OC00NmQ0LTkxN2QtOTE3NTRjMDAzMjlh")
            .build()

        return chain.proceed(request)

    }

}