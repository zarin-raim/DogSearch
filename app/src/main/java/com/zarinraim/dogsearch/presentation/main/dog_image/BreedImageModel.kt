package com.zarinraim.dogsearch.presentation.main.dog_image

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zarinraim.dogsearch.data.model.toDogImage
import com.zarinraim.dogsearch.domain.model.DogImage
import com.zarinraim.dogsearch.domain.repository.BreedsRepository
import kotlinx.coroutines.launch

/**
 * BreedImageModel provides access to a random image of a dog
 * depending on the clicked breed/sub-breed
 */
class BreedImageModel(
    private val repo: BreedsRepository,
    val breedName: String?,
    val subBreedName: String? = null
) : ViewModel() {
    val image: MutableState<DogImage> = mutableStateOf(DogImage(String()))

    init {
        getBreedImage(
            breedName = breedName,
            subBreedName = subBreedName
        )
    }

    private fun getBreedImage(breedName: String?, subBreedName: String?) {
        viewModelScope.launch {
            val imageSrc =
                if (breedName != null) {
                    when (subBreedName) {
                        null -> repo.getImageByBreed(breedName = breedName).toDogImage()
                        else -> repo.getImageBySubBreed(
                            breedName = breedName,
                            subBreedName = subBreedName
                        ).toDogImage()
                    }
                } else DogImage("")

            image.value = imageSrc
        }
    }
}