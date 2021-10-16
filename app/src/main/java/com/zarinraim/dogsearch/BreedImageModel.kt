package com.zarinraim.dogsearch

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BreedImageModel(val breedName: String?, val subBreedName: String? = null) : ViewModel() {
    val image: MutableState<String> = mutableStateOf(String())

    init {
        getBreedImage(
            breedName = breedName,
            subBreedName = subBreedName
        )
    }

    private fun getBreedImage(breedName: String?, subBreedName: String?) {
        if (breedName != null && subBreedName == null) {
            viewModelScope.launch {
                val listResult =
                    DogApi.retrofitService.getImageByBreed(breedName = breedName).message
                image.value = listResult
            }
        } else if (breedName != null && subBreedName != null) {
            viewModelScope.launch {
                val listResult =
                    DogApi.retrofitService.getImageBySubBreed(
                        breedName = breedName,
                        subBreedName = subBreedName
                    ).message
                image.value = listResult
            }
        }
    }
}