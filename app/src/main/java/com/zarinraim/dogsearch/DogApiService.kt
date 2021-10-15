package com.zarinraim.dogsearch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://dog.ceo/api/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

/**
 * Defines how Retrofit talks to the web server using HTTP requests
 */
interface DogApiService {
    @GET("breeds/list/all")
    suspend fun getAllBreeds(): ApiResponse
}

object DogApi {
    val retrofitService: DogApiService by lazy {
        retrofit.create(DogApiService::class.java)
    }
}