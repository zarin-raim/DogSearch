package com.zarinraim.dogsearch.ui.main.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.zarinraim.dogsearch.ui.main.view.BreedsListScreen
import com.zarinraim.dogsearch.ui.main.view.DogImageScreen
import com.zarinraim.dogsearch.ui.main.viewmodel.BreedImageModel
import com.zarinraim.dogsearch.ui.main.viewmodel.DogBreedsListModel

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.BreedsList.route
    ) {
        composable(
            route = Screen.BreedsList.route
        ) {
            val viewModel = DogBreedsListModel()
            BreedsListScreen(
                viewModel = viewModel,
                onClickOpenImage = { breedName, subBreedName ->
                    navigateToImageScreen(navController, breedName, subBreedName)
                }
            )
        }

        composable(
            route = Screen.DogImage.route + "/{breedName}/{subBreedName}",
            arguments = listOf(
                navArgument("breedName") {
                    type = NavType.StringType
                },
                navArgument("subBreedName") {
                    type = NavType.StringType
                }
            )
        ) { navBackStack ->
            val breedName = navBackStack.arguments?.getString("breedName")
            val subBreedName = navBackStack.arguments?.getString("subBreedName")

            val viewModel = BreedImageModel(breedName = breedName, subBreedName = subBreedName)
            DogImageScreen(viewModel)
        }

        composable(
            route = Screen.DogImage.route + "/{breedName}",
            arguments = listOf(
                navArgument("breedName") {
                    type = NavType.StringType
                }
            )
        ) { navBackStack ->
            val breedName = navBackStack.arguments?.getString("breedName")

            val viewModel = BreedImageModel(breedName = breedName)
            DogImageScreen(viewModel)
        }
    }
}

private fun navigateToImageScreen(
    navController: NavHostController,
    breedName: String,
    subBreedName: String = ""
) {
    val route = "${Screen.DogImage.route}/$breedName"

    if (subBreedName.isNotBlank())
        navController.navigate("$route/$subBreedName")
    else
        navController.navigate(route)
}