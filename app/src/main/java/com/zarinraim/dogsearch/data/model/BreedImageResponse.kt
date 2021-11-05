package com.zarinraim.dogsearch.data.model

import com.zarinraim.dogsearch.domain.model.DogImage


/**
 * Class to save DogApi response on fetching random dog image
 */
data class BreedImageResponse(
    val message: String,
    val status: String
)

fun BreedImageResponse.toDogImage(): DogImage {
    return DogImage(
        src = message
    )
}
