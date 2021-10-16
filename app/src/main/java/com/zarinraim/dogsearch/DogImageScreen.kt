package com.zarinraim.dogsearch

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun DogImageScreen(viewModel: BreedImageModel) {
    val imageLink = viewModel.image.value
    Text(
        text = "$imageLink",
        fontSize = 28.sp
    )
}