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
        viewModelScope.launch {
            val listResult = if (breedName != null) {
                when (subBreedName) {
                    null -> DogApi.retrofitService.getImageByBreed(breedName = breedName).message
                    else -> DogApi.retrofitService.getImageBySubBreed(
                        breedName = breedName,
                        subBreedName = subBreedName
                    ).message
                }
            } else ""

            image.value = listResult
        }
    }
}