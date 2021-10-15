package com.zarinraim.dogsearch

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DogBreedsListModel : ViewModel() {
    val dogBreeds: MutableState<Map<String, List<String>>> = mutableStateOf(mapOf())

    init {
        viewModelScope.launch {
            val listResult = DogApi.retrofitService.getAllBreeds().message

            dogBreeds.value = listResult
        }
    }
}