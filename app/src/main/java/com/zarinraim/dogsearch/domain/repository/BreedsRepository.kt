package com.zarinraim.dogsearch.domain.repository

import com.zarinraim.dogsearch.data.model.BreedImageResponse
import com.zarinraim.dogsearch.domain.model.Breeds

interface BreedsRepository {

    suspend fun getBreeds(): Breeds

    suspend fun getImageByBreed(breedName: String): BreedImageResponse

    suspend fun getImageBySubBreed(breedName: String, subBreedName: String): BreedImageResponse
}
