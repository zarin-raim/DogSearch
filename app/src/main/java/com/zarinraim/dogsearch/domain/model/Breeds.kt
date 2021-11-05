package com.zarinraim.dogsearch.domain.model

@JvmInline
value class Breeds(
    val value: Map<Breed, SubBreeds>
)