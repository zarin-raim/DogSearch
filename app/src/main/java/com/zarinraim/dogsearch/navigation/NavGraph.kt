package com.zarinraim.dogsearch.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.zarinraim.dogsearch.feature.list.BreedsListScreen
import com.zarinraim.dogsearch.feature.detail.DogImageScreen
import com.zarinraim.dogsearch.feature.detail.BreedImageViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

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
            BreedsListScreen(
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

            val viewModel = getViewModel<BreedImageViewModel> {
                parametersOf(breedName, subBreedName)
            }
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

            val viewModel = getViewModel<BreedImageViewModel> {
                parametersOf(breedName, "")
            }
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
