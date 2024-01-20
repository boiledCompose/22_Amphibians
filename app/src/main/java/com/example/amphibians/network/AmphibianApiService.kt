package com.example.amphibians.network

import com.example.amphibians.model.AmphibianItem
import retrofit2.http.GET

interface AmphibianApiService {
    @GET("amphibians")
    suspend fun getAmphibianList(): List<AmphibianItem>
}

