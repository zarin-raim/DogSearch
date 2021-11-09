package com.zarinraim.dogsearch.data.model

import com.zarinraim.dogsearch.domain.model.Breed
import com.zarinraim.dogsearch.domain.model.SubBreeds

/**
 * Class to save DogApi response on fetching all breeds list
 */
data class BreedsResponse(
    val message: Map<String, List<String>>,
    val status: String
)

fun BreedsResponse.toBreeds(): Map<Breed, SubBreeds> {
    return message.entries.associate {
        Breed(it.key) to SubBreeds(it.value)
    }
}
