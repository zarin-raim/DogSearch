package com.zarinraim.dogsearch.data.model

/**
 * Class to save DogApi response on fetching all breeds list
 */
data class AllBreedsResponse(
    val message: Map<String, List<String>>,
    val status: String
)