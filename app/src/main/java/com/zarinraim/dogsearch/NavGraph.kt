package com.zarinraim.dogsearch

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun SetupNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.BreedsList.route
    ) {
        composable(
            route = Screen.BreedsList.route
        ) {
            val viewModel = DogBreedsListModel()
            BreedsListScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            route = Screen.DogImage.route + "/{breedName}"
        ) { navBackStack ->
            val breedName = navBackStack.arguments?.getString("breedName")

            val viewModel = BreedImageModel(breedName = breedName)
            DogImageScreen(viewModel)
        }
        composable(
            route = Screen.DogImage.route + "/{breedName}/{subBreedName}"
        ) { navBackStack ->
            val breedName = navBackStack.arguments?.getString("breedName")
            val subBreedName = navBackStack.arguments?.getString("subBreedName")

            val viewModel = BreedImageModel(breedName = breedName, subBreedName = subBreedName)
            DogImageScreen(viewModel)
        }
    }
}