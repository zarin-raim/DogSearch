package com.zarinraim.dogsearch.ui.main.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zarinraim.dogsearch.data.api.DogApi
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