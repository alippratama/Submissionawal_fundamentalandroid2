package com.example.submissionawal_fundamentalandroid.utils

sealed class Result{
    data class Success<out T>(val data: T) : Result()
    data class Error(val exception: Throwable) : Result()
    data class loading(val isloading: Boolean) : Result()
}