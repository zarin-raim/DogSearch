package com.zarinraim.dogsearch.domain.repository

import com.zarinraim.dogsearch.domain.model.Breeds
import com.zarinraim.dogsearch.domain.model.DogImage

interface BreedsRepository {

    suspend fun getBreeds(): Breeds

    suspend fun getImageByBreed(breedName: String): DogImage

    suspend fun getImageBySubBreed(breedName: String, subBreedName: String): DogImage
}
