package com.zarinraim.dogsearch.presentation.main.dog_image

data class BreedImageState(
    val isLoading: Boolean = false,
    val src: String = "",
    val error: String = ""
)
