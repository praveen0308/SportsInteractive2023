package com.example.sportsinteractive2023.network

import com.example.sportsinteractive2023.models.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {

    @Headers(
        "Content-Type: application/json;charset=utf-8",
        "Accept: application/json",
        "Accept-Encoding: identity"
    )
    @GET("{matchCode}.json")
    suspend fun fetchMatchDetail(@Path("matchCode") matchCode: String): ApiResponse
}