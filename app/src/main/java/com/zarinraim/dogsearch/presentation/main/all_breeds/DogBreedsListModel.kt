package com.zarinraim.dogsearch.presentation.main.all_breeds

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zarinraim.dogsearch.data.model.toBreeds
import com.zarinraim.dogsearch.domain.model.Breeds
import com.zarinraim.dogsearch.domain.repository.BreedsRepository
import kotlinx.coroutines.launch

class DogBreedsListModel(private val repo: BreedsRepository) : ViewModel() {
    val dogBreeds: MutableState<Breeds> = mutableStateOf(Breeds(mapOf()))

    init {
        viewModelScope.launch {
            val listResult = repo.getBreeds().toBreeds()

            dogBreeds.value = listResult
        }
    }
}