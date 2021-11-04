package com.zarinraim.dogsearch.di

import com.zarinraim.dogsearch.data.repository.BreedRepositoryImpl
import com.zarinraim.dogsearch.domain.repository.BreedsRepository
import com.zarinraim.dogsearch.presentation.main.all_breeds.DogBreedsListModel
import com.zarinraim.dogsearch.presentation.main.dog_image.BreedImageModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<BreedsRepository> { BreedRepositoryImpl() }
    viewModel { DogBreedsListModel(get()) }
    viewModel { (breedName: String, subBreedName: String) ->
        BreedImageModel(
            get(),
            breedName,
            subBreedName
        )
    }
}