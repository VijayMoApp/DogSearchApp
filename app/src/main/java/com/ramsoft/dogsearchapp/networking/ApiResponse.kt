package com.ramsoft.dogsearchapp.networking

sealed class ApiResponse<T>(
    val data: T? = null,
    val message: String? = null) {

    class  Success<T>(data: T) : ApiResponse<T>(data)
    class Failure<T>(message: String? , data: T? = null) : ApiResponse<T>(data, message)
    class  Loading<T> :  ApiResponse<T>()
}

