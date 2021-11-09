package com.zarinraim.dogsearch.feature.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zarinraim.dogsearch.domain.model.Breed
import com.zarinraim.dogsearch.domain.model.Breeds
import com.zarinraim.dogsearch.domain.model.SubBreeds
import com.zarinraim.dogsearch.domain.repository.BreedsRepository
import kotlinx.coroutines.launch

class BreedsListViewModel(private val repo: BreedsRepository) : ViewModel() {

    private val _state = mutableStateOf(BreedsListState())
    val state: State<BreedsListState> = _state

    init {
        getBreeds()
    }

    private fun getBreeds() = viewModelScope.launch {
        _state.value = BreedsListState(isLoading = true)

        when (val result = repo.getBreeds()) {
            is Breeds.DogBreeds -> {
                val breeds = result.values
                _state.value = BreedsListState(breeds = breeds)
            }
            is Breeds.Failure ->
                _state.value = BreedsListState(error = result.error)
        }
    }

    fun refresh() {
        getBreeds()
    }
}

data class BreedsListState(
    val isLoading: Boolean = false,
    val breeds: Map<Breed, SubBreeds> = emptyMap(),
    val error: String = ""
)
