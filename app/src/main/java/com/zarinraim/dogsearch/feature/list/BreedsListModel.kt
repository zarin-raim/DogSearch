package com.zarinraim.dogsearch.feature.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zarinraim.dogsearch.data.model.toBreeds
import com.zarinraim.dogsearch.domain.repository.BreedsRepository
import kotlinx.coroutines.launch

class BreedsListModel(private val repo: BreedsRepository) : ViewModel() {

    private val _state = mutableStateOf(BreedsListState())
    val state: State<BreedsListState> = _state

    init {
        viewModelScope.launch {
            _state.value = BreedsListState(breeds = repo.getBreeds().toBreeds())
        }
    }
}