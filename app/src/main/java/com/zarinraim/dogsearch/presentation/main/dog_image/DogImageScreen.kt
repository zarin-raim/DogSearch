package com.zarinraim.dogsearch.presentation.main.dog_image

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.zarinraim.dogsearch.R

@ExperimentalCoilApi
@Composable
fun DogImageScreen(viewModel: BreedImageModel) {
    val imageLink = viewModel.image.value

    DogImage(
        imageLink = imageLink.src,
        breedName = viewModel.breedName!!,
        subBreedName = viewModel.subBreedName
    )
}

@ExperimentalCoilApi
@Composable
fun DogImage(
    imageLink: String,
    breedName: String,
    subBreedName: String?
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val painter = rememberImagePainter(imageLink)
        val painterState = painter.state
        if (painterState is ImagePainter.State.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
        val configuration = LocalConfiguration.current

        when (configuration.orientation) {

            Configuration.ORIENTATION_LANDSCAPE -> {

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Content(
                        painter = painter,
                        imageModifier = Modifier
                            .fillMaxSize()
                            .weight(2f),
                        buttonModifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        breedName = breedName,
                        subBreedName = subBreedName
                    )
                }
            }
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Content(
                        painter = painter,
                        imageModifier = Modifier
                            .fillMaxSize()
                            .weight(3f),
                        buttonModifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        breedName = breedName,
                        subBreedName = subBreedName
                    )
                }
            }
        }
    }
}

@Composable
fun Content(
    painter: ImagePainter,
    imageModifier: Modifier,
    buttonModifier: Modifier,
    breedName: String,
    subBreedName: String?
) {
    Image(
        painter = painter,
        contentDescription = null,
        modifier = imageModifier,
        alignment = Alignment.CenterStart
    )

    Column(
        modifier = buttonModifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val caption = stringResource(id = R.string.breed_caption, breedName, subBreedName ?: "")

        Text(
            text = caption,
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center
        )

//        OutlinedButton(
//            onClick = {
//
//            }
//        ) {
//            Text(
//                text = "Next Random Image",
//                style = MaterialTheme.typography.button
//            )
//        }
    }
}
