package com.zarinraim.dogsearch.data.model

import com.zarinraim.dogsearch.domain.model.AllBreeds
import com.zarinraim.dogsearch.domain.model.Breed
import com.zarinraim.dogsearch.domain.model.SubBreeds

/**
 * Class to save DogApi response on fetching all breeds list
 */
data class AllBreedsResponse(
    val message: Map<String, List<String>>,
    val status: String
)

fun AllBreedsResponse.toAllBreeds(): AllBreeds {
    val mapOfBreeds: MutableMap<Breed, SubBreeds> = mutableMapOf()
    for (item in message) {
        mapOfBreeds[Breed(item.key)] = SubBreeds(item.value)
    }

    return AllBreeds(
        map = mapOfBreeds
    )
}