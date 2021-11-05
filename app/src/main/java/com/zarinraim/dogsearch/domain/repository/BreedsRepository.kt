package com.zarinraim.dogsearch.domain.repository

import com.zarinraim.dogsearch.data.model.BreedsResponse
import com.zarinraim.dogsearch.data.model.BreedImageResponse

interface BreedsRepository {

    suspend fun getBreeds(): BreedsResponse

    suspend fun getImageByBreed(breedName: String): BreedImageResponse

    suspend fun getImageBySubBreed(breedName: String, subBreedName: String): BreedImageResponse
}