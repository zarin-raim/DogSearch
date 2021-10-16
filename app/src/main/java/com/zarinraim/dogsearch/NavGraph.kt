package com.zarinraim.dogsearch

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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
            BreedsListScreen()
        }
        composable(
            route = Screen.DogImage.route
        ) {
            DogImageScreen()
        }
    }
}