package com.zarinraim.dogsearch.domain.model

import com.zarinraim.dogsearch.utils.PageError

sealed interface DogImage {
    data class Image(val src: String) : DogImage
    data class Failure(val error: PageError) : DogImage
}
