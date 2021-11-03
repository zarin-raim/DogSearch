package com.zarinraim.dogsearch.domain.repository

import com.zarinraim.dogsearch.data.model.AllBreedsResponse
import com.zarinraim.dogsearch.data.model.BreedImageResponse

interface BreedsRepository {

    suspend fun getAllBreeds(): AllBreedsResponse

    suspend fun getImageByBreed(breedName: String): BreedImageResponse

    suspend fun getImageBySubBreed(breedName: String, subBreedName: String): BreedImageResponse
}