package com.pragmanila.awrasafely.api.model

import android.graphics.Bitmap
import com.google.gson.JsonObject
//import com.pragmanila.awrasafely.api.model.getprofile.GetProfileResponse
//import com.pragmanila.awrasafely.api.model.login.LoginPost
//import com.pragmanila.awrasafely.api.model.login.LoginResponse
//import com.pragmanila.awrasafely.api.model.newsfeed.GetNewsFeed
//import com.pragmanila.awrasafely.api.model.pragin_pragout.GetPostInOut
//import com.pragmanila.awrasafely.api.model.viewallrequest.ViewAllRequest
import com.pragmanila.awrasafely.utils.TinyDB
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.POST
import retrofit2.http.Multipart



interface Api {

//    @FormUrlEncoded
//    @POST("api/v1/auth/signin")
//    fun login(
//        @Body() body: JsonObject
//    ):Call<LoginResponse>
//    @POST("/api/v1/auth/signin")
//    @Headers("Content-Type: application/json")
//    fun login(@Body body: JsonObject): Call<LoginResponse>
//
//    @GET("/api/v1/user/{userId}")
//    fun getUserProfile(@Path("userId") id: String): Call<GetProfileResponse>
//
//    @FormUrlEncoded
//    @POST("/device/{deviceId}/access/{accessCode}/prag")
//    fun pragInOut(
//        @Path("deviceId") deviceId: String,
//        @Path("accessCode") accessCode: String,
//        @Field("prag_image") prag_image: String
//    ): Call<GetPostInOut>
//
//    @GET("/device/{deviceId}/access/{accessCode}")
//    fun getPragInOut(
//        @Path("deviceId") deviceId: String,
//        @Path("accessCode") accessCode: String
//    ): Call<GetPostInOut>
//
////    ?filter=requestType:leave&items-per-page=1000
//    @GET("/api/v1/request")
//    @Headers("Content-Type: application/json")
//    fun getViewAllRequest(
//        @Header("Authorization") token: String
//    ): Call<ViewAllRequest>
//
//
//
//    @POST("/api/v1/feed")
//    fun getFeed(
//        @Query("page") page: String,
//        @Query("items-per-page") itemsPerPage: String
//    ): Call<GetNewsFeed>
//
//    @Headers("Content-Type: application/json;charset=UTF-8")
//    @POST("device/{uniqueID}/access/{code}/prag")
//    fun prag(
//        @Path("uniqueID") deviceId: String,
//        @Path("code") accessCode: String,
//        @Body string: String
//    ): Call<Any>


}