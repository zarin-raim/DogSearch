package com.zarinraim.dogsearch.data.repository

import com.zarinraim.dogsearch.data.api.DogApi
import com.zarinraim.dogsearch.data.model.BreedImageResponse
import com.zarinraim.dogsearch.data.model.toBreeds
import com.zarinraim.dogsearch.domain.model.Breeds
import com.zarinraim.dogsearch.domain.repository.BreedsRepository
import retrofit2.HttpException
import java.io.IOException

class BreedRepositoryImpl : BreedsRepository {

    override suspend fun getBreeds(): Breeds = runCatching {
        DogApi.retrofitService.getBreeds().toBreeds()
    }.fold(
        onSuccess = { breedsResponse ->
            Breeds.DogBreeds(values = breedsResponse)
        },
        onFailure = { e ->
            when (e) {
                is HttpException -> {
                    Breeds.Failure(error = e.localizedMessage ?: "An unexpected error occurred.")
                }

                is IOException -> {
                    Breeds.Failure(error = "Please, check your internet connection and try again.")
                }
                else -> Breeds.Failure(error = "An unexpected error occurred.")
            }

        }
    )

    override suspend fun getImageByBreed(
        breedName: String
    ): BreedImageResponse {
        return DogApi.retrofitService.getImageByBreed(breedName)
    }

    override suspend fun getImageBySubBreed(
        breedName: String, subBreedName: String
    ): BreedImageResponse {
        return DogApi.retrofitService.getImageBySubBreed(breedName, subBreedName)
    }
}
