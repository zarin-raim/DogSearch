package com.zarinraim.dogsearch.di

import com.zarinraim.dogsearch.data.repository.BreedRepositoryImpl
import com.zarinraim.dogsearch.domain.repository.BreedsRepository
import com.zarinraim.dogsearch.feature.list.BreedsListViewModel
import com.zarinraim.dogsearch.feature.detail.BreedImageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<BreedsRepository> { BreedRepositoryImpl() }
    viewModel { BreedsListViewModel(get()) }
    viewModel { (breedName: String, subBreedName: String) ->
        BreedImageViewModel(
            get(),
            breedName,
            subBreedName
        )
    }
}
