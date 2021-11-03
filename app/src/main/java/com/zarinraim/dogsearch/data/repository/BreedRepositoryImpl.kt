package com.zarinraim.dogsearch.data.repository

import com.zarinraim.dogsearch.data.api.DogApi
import com.zarinraim.dogsearch.data.model.AllBreedsResponse
import com.zarinraim.dogsearch.data.model.BreedImageResponse
import com.zarinraim.dogsearch.domain.repository.BreedsRepository

class BreedRepositoryImpl : BreedsRepository {
    override suspend fun getAllBreeds(): AllBreedsResponse {
        return DogApi.retrofitService.getAllBreeds()
    }

    override suspend fun getImageByBreed(breedName: String): BreedImageResponse {
        return DogApi.retrofitService.getImageByBreed(breedName)
    }

    override suspend fun getImageBySubBreed(
        breedName: String, subBreedName: String
    ): BreedImageResponse {
        return DogApi.retrofitService.getImageBySubBreed(breedName, subBreedName)
    }
}