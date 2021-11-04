package com.zarinraim.dogsearch.feature.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zarinraim.dogsearch.data.model.toDogImage
import com.zarinraim.dogsearch.domain.repository.BreedsRepository
import kotlinx.coroutines.launch

/**
 * BreedImageModel provides access to a random image of a dog
 * depending on the clicked breed/sub-breed
 */
class BreedImageModel(
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
            _state.value =
                when (subBreedName) {
                    "" -> BreedImageState(
                        src = repo.getImageByBreed(breedName = breedName).toDogImage().src
                    )
                    else -> BreedImageState(
                        src = repo.getImageBySubBreed(
                            breedName = breedName,
                            subBreedName = subBreedName
                        ).toDogImage().src
                    )
                }
        }
    }
}