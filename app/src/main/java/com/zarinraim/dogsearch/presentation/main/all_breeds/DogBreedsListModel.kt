package com.zarinraim.dogsearch.presentation.main.all_breeds

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zarinraim.dogsearch.data.model.toAllBreeds
import com.zarinraim.dogsearch.domain.model.AllBreeds
import com.zarinraim.dogsearch.domain.repository.BreedsRepository
import kotlinx.coroutines.launch

class DogBreedsListModel(private val repo: BreedsRepository) : ViewModel() {
    val dogBreeds: MutableState<AllBreeds> = mutableStateOf(AllBreeds(mapOf()))

    init {
        viewModelScope.launch {
            val listResult = repo.getAllBreeds().toAllBreeds()

            dogBreeds.value = listResult
        }
    }
}