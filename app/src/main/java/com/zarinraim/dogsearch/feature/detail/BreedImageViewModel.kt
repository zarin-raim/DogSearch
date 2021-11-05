package com.zarinraim.dogsearch.feature.detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.zarinraim.dogsearch.data.model.toBreeds
import com.zarinraim.dogsearch.data.model.toDogImage
import com.zarinraim.dogsearch.domain.repository.BreedsRepository
import com.zarinraim.dogsearch.feature.list.BreedsListState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

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
            try {
                _state.value = BreedImageState(isLoading = true)

                val imageSrc = when (subBreedName) {
                    "" -> repo.getImageByBreed(breedName = breedName).toDogImage().src
                    else -> repo.getImageBySubBreed(
                        breedName = breedName,
                        subBreedName = subBreedName
                    ).toDogImage().src
                }

                _state.value = BreedImageState(src = imageSrc)
            } catch (e: HttpException) {
                _state.value = BreedImageState(
                    error = e.localizedMessage ?: "An unexpected error occurred."
                )
            } catch (e: IOException) {
                Log.e("IOException:", "$e.localizedMessage")
                _state.value = BreedImageState(
                    error = "Please, check your internet connection and try again."
                )
            }
        }
    }

    fun getNextRandomImage(){
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
