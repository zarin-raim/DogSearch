package com.zarinraim.dogsearch.domain.model

sealed interface Breeds {
    data class DogBreeds(val values: Map<Breed, SubBreeds>) : Breeds
    data class Failure(val error: String) : Breeds
}
