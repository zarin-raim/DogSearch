package com.zarinraim.dogsearch.feature.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zarinraim.dogsearch.domain.model.DogImage
import com.zarinraim.dogsearch.domain.repository.BreedsRepository
import kotlinx.coroutines.launch

/**
 * BreedImageModel provides access to a random image of a dog
 * depending on the clicked breed/sub-breed
 */
class BreedImageViewModel(
    private val repo: BreedsRepository,
    val breedName: String,
    val subBreedName: String
) : ViewModel() {

    private val _state = mutableStateOf(BreedImageState())
    val state: State<BreedImageState> = _state

    init {
        getBreedImage(
            breedName = breedName,
            subBreedName = subBreedName
        )
    }

    private fun getBreedImage(breedName: String, subBreedName: String) {
        viewModelScope.launch {
            _state.value = BreedImageState(isLoading = true)

            val result = if (subBreedName.isEmpty()) {
                repo.getImageByBreed(breedName = breedName)
            } else {
                repo.getImageBySubBreed(breedName = breedName, subBreedName = subBreedName)
            }

            when (result) {
                is DogImage.Image -> _state.value = BreedImageState(src = result.src)
                is DogImage.Failure -> _state.value = BreedImageState(error = result.error.msg)
            }
        }
    }

    fun getNextRandomImage() {
        getBreedImage(
            breedName = breedName,
            subBreedName = subBreedName
        )
    }
}

data class BreedImageState(
    val isLoading: Boolean = false,
    val src: String = "",
    val error: String = ""
)
