package com.zarinraim.dogsearch

data class ApiResponse(
    val message: Map<String, List<String>>,
    val status: String
)