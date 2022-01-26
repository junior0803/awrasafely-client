package com.pragmanila.awrasafely.repository

import com.pragmanila.awrasafely.api.RetrofitClient
import com.pragmanila.awrasafely.api.model.Post
import com.pragmanila.awrasafely.api.model.User
import com.pragmanila.awrasafely.api.model.UserResponse
import retrofit2.Response


//class Repository {
//
//    suspend fun getPost(): Response<List<Post>> {
//        return RetrofitClient.api.getPost()
//    }
//
//    suspend fun pushPost(user: User): Response<UserResponse> {
//        return RetrofitClient.api.pushPost(user)
//    }
//}