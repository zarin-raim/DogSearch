package com.zarinraim.dogsearch.data.api

import com.zarinraim.dogsearch.data.model.AllBreedsResponse
import com.zarinraim.dogsearch.data.model.BreedImageResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

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
    suspend fun getAllBreeds(): AllBreedsResponse

    @GET("breed/{breedName}/images/random")
    suspend fun getImageByBreed(
        @Path("breedName") breedName: String
    ): BreedImageResponse

    @GET("breed/{breedName}/{subBreedName}/images/random")
    suspend fun getImageBySubBreed(
        @Path("breedName") breedName: String,
        @Path("subBreedName") subBreedName: String
    ): BreedImageResponse
}

object DogApi {
    val retrofitService: DogApiService by lazy {
        retrofit.create(DogApiService::class.java)
    }
}