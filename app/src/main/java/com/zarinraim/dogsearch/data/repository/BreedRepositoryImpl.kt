package com.zarinraim.dogsearch.data.repository

import com.zarinraim.dogsearch.data.api.DogApi
import com.zarinraim.dogsearch.data.model.toBreeds
import com.zarinraim.dogsearch.data.model.toDogImage
import com.zarinraim.dogsearch.domain.model.Breeds
import com.zarinraim.dogsearch.domain.model.DogImage
import com.zarinraim.dogsearch.domain.repository.BreedsRepository
import com.zarinraim.dogsearch.utils.toPageError

class BreedRepositoryImpl : BreedsRepository {

    override suspend fun getBreeds(): Breeds = runCatching {
        DogApi.retrofitService.getBreeds().toBreeds()
    }.fold(
        onSuccess = { Breeds.DogBreeds(values = it) },
        onFailure = { Breeds.Failure(toPageError(it)) }
    )

    override suspend fun getImageByBreed(breedName: String): DogImage = runCatching {
        DogApi.retrofitService.getImageByBreed(breedName).toDogImage()
    }.fold(
        onSuccess = { DogImage.Image(src = it) },
        onFailure = { DogImage.Failure(toPageError(it)) }
    )

    override suspend fun getImageBySubBreed(
        breedName: String, subBreedName: String
    ): DogImage = runCatching {
        DogApi.retrofitService.getImageBySubBreed(breedName, subBreedName).toDogImage()
    }.fold(
        onSuccess = { DogImage.Image(src = it) },
        onFailure = { DogImage.Failure(toPageError(it)) }
    )

}
