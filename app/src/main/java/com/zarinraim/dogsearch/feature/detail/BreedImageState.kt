package com.zarinraim.dogsearch.feature.detail

data class BreedImageState(
    val isLoading: Boolean = false,
    val src: String = "",
    val error: String = ""
)
