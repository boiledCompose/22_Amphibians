package com.example.amphibians.network

import com.example.amphibians.network.model.AmphibianItem
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

const val BASE_URL:String = "https://android-kotlin-fun-mars-server.appspot.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface AmphibianApiService {
    @GET("amphibians")
    suspend fun getAmphibianList(): List<AmphibianItem>
}

object AmphibianApi{
    val retrofitService:AmphibianApiService by lazy{
        retrofit.create(AmphibianApiService::class.java)
    }
}