package com.zarinraim.dogsearch.presentation.main.all_breeds

import com.zarinraim.dogsearch.domain.model.Breeds

data class BreedsListState(
    val isLoading: Boolean = false,
    val breeds: Breeds = Breeds(emptyMap()),
    val error: String = ""
)