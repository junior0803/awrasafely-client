package com.pragmanila.awrasafely.api

import com.pragmanila.awrasafely.api.model.Post
import com.pragmanila.awrasafely.api.model.User
import com.pragmanila.awrasafely.api.model.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("/core/api/v1/labels")
    suspend fun getPost(): Response<List<Post>>

    @POST("/core/api/v1/clients/info")
    fun pushPost(@Body param: User): Call<UserResponse>
}