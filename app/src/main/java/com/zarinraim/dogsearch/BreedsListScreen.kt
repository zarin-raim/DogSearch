package com.zarinraim.dogsearch

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@ExperimentalMaterialApi
@Composable
fun BreedsListScreen(
    navController: NavController,
    viewModel: DogBreedsListModel = DogBreedsListModel()
) {
    val dogBreeds = viewModel.dogBreeds.value

    Box(modifier = Modifier.fillMaxSize()) {
        if (dogBreeds.isNotEmpty()) {
            BreedsList(
                breeds = dogBreeds,
                navController = navController
            )
        } else {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun BreedsList(
    breeds: Map<String, List<String>>,
    navController: NavController
) {
    val mainBreedsList = breeds.keys.toList()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(mainBreedsList) { dogBreed ->
            BreedItem(
                breedName = dogBreed,
                subBreeds = breeds[dogBreed],
                navController = navController
            )
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun BreedItem(
    breedName: String,
    subBreeds: List<String>?,
    navController: NavController
) {
    val expanded = remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expanded.value) 180f else 0f
    )

    val hasSubBreeds = subBreeds!!.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (hasSubBreeds) {
                    expanded.value = !expanded.value
                } else {
                    navController.navigate(
                        route = Screen.DogImage.route + "/$breedName"
                    )
                }
            }
    ) {
        Surface(
            color = MaterialTheme.colors.primary
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)
            ) {

                Text(
                    text = breedName,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .weight(6f)
                )

                if (hasSubBreeds) {
                    IconButton(
                        modifier = Modifier
                            .weight(1f)
                            .alpha(ContentAlpha.medium)
                            .rotate(rotationState),
                        onClick = {
                            expanded.value = !expanded.value
                        }) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Drop-Down Arrow"
                        )
                    }
                }
            }
        }

        if (hasSubBreeds && expanded.value) {
            SubBreedList(
                breedName = breedName,
                subBreeds = subBreeds,
                navController = navController
            )
        }
    }
}

@Composable
fun SubBreedList(
    breedName: String,
    subBreeds: List<String>,
    navController: NavController
) {
    Column {
        SubBreedItem(
            breedName = breedName,
            subBreedName = "",
            navController = navController
        )
        for (subBreedName in subBreeds) {
            SubBreedItem(
                breedName = breedName,
                subBreedName = subBreedName,
                navController = navController
            )
            Divider()
        }
    }
}

@Composable
fun SubBreedItem(
    breedName: String,
    subBreedName: String,
    navController: NavController
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                var request = "/$breedName"
                request += if (subBreedName.isNotBlank()) {
                    "/$subBreedName"
                } else ""

                navController.navigate(
                    route = Screen.DogImage.route + request
                )
            }
    ) {
        Text(
            text = if (subBreedName.isBlank()) "Any" else subBreedName,
            fontSize = 22.sp,
            modifier = Modifier
                .weight(6f)
        )

        IconButton(
            modifier = Modifier
                .weight(1f)
                .alpha(ContentAlpha.medium),
            onClick = {

            }) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Forward Arrow"
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun PreviewDogBreedList() {
    BreedsList(
        breeds = mapOf(
            "Corgi" to listOf("cardigan"),
            "Dingo" to listOf(),
            "Hound" to listOf("afghan", "basset")
        ),
        navController = rememberNavController()
    )
}

