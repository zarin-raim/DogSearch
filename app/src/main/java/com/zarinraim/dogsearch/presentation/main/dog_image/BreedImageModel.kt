package com.zarinraim.dogsearch.presentation.main.dog_image

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zarinraim.dogsearch.data.api.DogApi
import kotlinx.coroutines.launch

/**
 * BreedImageModel provides access to a random image of a dog
 * depending on the clicked breed/sub-breed
 */
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
            val listResult =
                if (breedName != null) {
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