package com.zarinraim.dogsearch.domain.model

import com.zarinraim.dogsearch.utils.PageError

sealed interface Breeds {
    data class DogBreeds(val values: Map<Breed, SubBreeds>) : Breeds
    data class Failure(val error: PageError) : Breeds
}
