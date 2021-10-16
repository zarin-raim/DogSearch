package com.zarinraim.dogsearch

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BreedImageModel(val breedName: String?) : ViewModel() {
    val image: MutableState<String> = mutableStateOf(String())

    init {
        getBreedImage(breedName)
    }

    private fun getBreedImage(breedName: String?){
        if (breedName != null) {
            viewModelScope.launch {
                val listResult =
                    DogApi.retrofitService.getImageByBreed(breedName = breedName).message

                image.value = listResult
            }
        }
    }
}