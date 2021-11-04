package com.zarinraim.dogsearch.navigation

sealed class Screen(val route: String) {
    object BreedsList : Screen(route = "breeds_list_screen")
    object DogImage : Screen(route = "dog_image_screen")
}
