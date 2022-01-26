package com.pragmanila.awrasafely.api.model

data class Post (
    val id: Int,
    val text:String,
    val type: String,
    val identifier: String,
    val description: String
)

data class User(
    val motherName: String,
    val fatherName: String,
    val birthOrder: Int,
    val birthDate: String
)


data class UserResponse(
    val success: String,
    val message: String
)
data class error(
    val title: String,
    val message: String
)