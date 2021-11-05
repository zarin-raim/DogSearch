package com.zarinraim.dogsearch.feature.list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zarinraim.dogsearch.data.model.toBreeds
import com.zarinraim.dogsearch.domain.model.Breeds
import com.zarinraim.dogsearch.domain.repository.BreedsRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class BreedsListViewModel(private val repo: BreedsRepository) : ViewModel() {

    private val _state = mutableStateOf(BreedsListState())
    val state: State<BreedsListState> = _state

    init {
        getBreeds()
    }

    private fun getBreeds() {
        viewModelScope.launch {
            try {
                _state.value = BreedsListState(isLoading = true)
                val breeds = repo.getBreeds().toBreeds()
                _state.value = BreedsListState(breeds = breeds)
            } catch (e: HttpException) {
                _state.value = BreedsListState(
                    error = e.localizedMessage ?: "An unexpected error occurred."
                )
            } catch (e: IOException) {
                Log.e("IOException:", "$e.localizedMessage")
                _state.value = BreedsListState(
                    error = "Please, check your internet connection and try again."
                )
            }
        }
    }
}

data class BreedsListState(
    val isLoading: Boolean = false,
    val breeds: Breeds = Breeds(emptyMap()),
    val error: String = ""
)
