package com.example.sportsinteractive2023.network

import com.example.sportsinteractive2023.models.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("{matchId}.json")
    suspend fun fetchMatchDetail(@Path("matchId") matchId:String) : ApiResponse
}