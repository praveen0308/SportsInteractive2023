package com.example.sportsinteractive2023.repository

import com.example.sportsinteractive2023.models.ApiResponse
import com.example.sportsinteractive2023.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MatchRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun fetchMatchDetail(matchCode:String): Flow<ApiResponse> {
        return flow {
            val response = apiService.fetchMatchDetail(matchCode)
            emit(response)
        }.flowOn(Dispatchers.IO)
    }

}