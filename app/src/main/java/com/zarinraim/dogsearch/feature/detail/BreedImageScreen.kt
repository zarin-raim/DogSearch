package com.zarinraim.dogsearch.feature.detail

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
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
fun DogImageScreen(viewModel: BreedImageViewModel) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val painter = rememberImagePainter(state.src)
        val painterState = painter.state

        DogImage(
            painter = painter,
            viewModel = viewModel
        )

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
        }

        if (state.isLoading || painterState is ImagePainter.State.Loading) {
            CircularProgressIndicator()
        }
    }
}

@ExperimentalCoilApi
@Composable
fun DogImage(
    painter: ImagePainter,
    viewModel: BreedImageViewModel
) {
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
                    viewModel = viewModel
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
                        .weight(IMAGE_WEIGHT),
                    buttonModifier = Modifier
                        .fillMaxSize()
                        .weight(TITLE_WEIGHT),
                    viewModel = viewModel
                )
            }
        }
    }
}


@Composable
fun Content(
    painter: ImagePainter,
    imageModifier: Modifier,
    buttonModifier: Modifier,
    viewModel: BreedImageViewModel
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
        val caption = stringResource(id = R.string.breed_caption, viewModel.breedName, viewModel.subBreedName)

        Text(
            text = caption,
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center
        )

        OutlinedButton(
            onClick = {
                viewModel.getNextRandomImage()
            }
        ) {
            Text(
                text = "Next Random Image",
                style = MaterialTheme.typography.button
            )
        }
    }
}

private const val IMAGE_WEIGHT = 6f
private const val TITLE_WEIGHT = 1f
