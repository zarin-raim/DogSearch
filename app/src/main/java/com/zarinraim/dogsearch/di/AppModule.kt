package com.zarinraim.dogsearch.di

import com.zarinraim.dogsearch.data.repository.BreedRepositoryImpl
import com.zarinraim.dogsearch.domain.repository.BreedsRepository
import com.zarinraim.dogsearch.feature.list.BreedsListModel
import com.zarinraim.dogsearch.feature.detail.BreedImageModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<BreedsRepository> { BreedRepositoryImpl() }
    viewModel { BreedsListModel(get()) }
    viewModel { (breedName: String, subBreedName: String) ->
        BreedImageModel(
            get(),
            breedName,
            subBreedName
        )
    }
}