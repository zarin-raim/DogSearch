package com.zarinraim.dogsearch

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

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
    }
}