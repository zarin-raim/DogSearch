package com.zarinraim.dogsearch

data class AllBreedsResponse(
    val message: Map<String, List<String>>,
    val status: String
)