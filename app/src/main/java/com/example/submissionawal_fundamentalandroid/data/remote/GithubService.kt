package com.example.submissionawal_fundamentalandroid.data.remote

import com.example.submissionawal_fundamentalandroid.data.model.ResponseUserGithub
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GithubService {
    @JvmSuppressWildcards
    @GET ( "users")
    suspend fun getUserGithub(): ResponseUserGithub
}