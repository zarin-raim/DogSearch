package com.zarinraim.dogsearch.presentation.main.all_breeds

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zarinraim.dogsearch.R
import com.zarinraim.dogsearch.domain.model.Breeds
import com.zarinraim.dogsearch.domain.model.Breed
import com.zarinraim.dogsearch.domain.model.SubBreeds
import org.koin.androidx.compose.viewModel

@ExperimentalMaterialApi
@Composable
fun BreedsListScreen(
    onClickOpenImage: (String, String) -> Unit = { _, _ -> }
) {
    // lazy inject ViewModel
    val viewModel: DogBreedsListModel by viewModel()
    // get AllBreeds object from ViewModel
    val allBreeds = viewModel.dogBreeds.value

    val listState = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (allBreeds.map.isNotEmpty()) {
            BreedsList(
                breeds = allBreeds,
                listState = listState,
                onClickOpenImage = onClickOpenImage
            )
        } else {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun BreedsList(
    breeds: Breeds,
    listState: LazyListState,
    onClickOpenImage: (String, String) -> Unit
) {
    val mainBreedsList = breeds.map.keys.toList()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = listState
    ) {

        items(mainBreedsList) { breed ->
            BreedItem(
                breed = breed,
                subBreeds = breeds.map[breed],
                onClickOpenImage = onClickOpenImage
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun BreedItem(
    breed: Breed,
    subBreeds: SubBreeds?,
    onClickOpenImage: (String, String) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expanded.value) 180f else 0f
    )

    val hasSubBreeds = subBreeds!!.list.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Surface(
            color = MaterialTheme.colors.primary
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        if (hasSubBreeds) {
                            expanded.value = !expanded.value
                        } else {
                            onClickOpenImage(breed.name, "")
                        }
                    }
            ) {

                Text(
                    text = breed.name,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .weight(6f)
                        .padding(8.dp)
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
                breedName = breed.name,
                subBreeds = subBreeds.list,
                onClickOpenImage = onClickOpenImage
            )
        }
    }
}

@Composable
fun SubBreedList(
    breedName: String,
    subBreeds: List<String>,
    onClickOpenImage: (String, String) -> Unit
) {
    Column {
        SubBreedItem(
            breedName = breedName,
            subBreedName = "",
            onClickOpenImage = onClickOpenImage
        )
        for (subBreedName in subBreeds) {
            SubBreedItem(
                breedName = breedName,
                subBreedName = subBreedName,
                onClickOpenImage = onClickOpenImage
            )
            Divider()
        }
    }
}

@Composable
fun SubBreedItem(
    breedName: String,
    subBreedName: String,
    onClickOpenImage: (String, String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                onClickOpenImage(breedName, subBreedName)
            }
    ) {
        Text(
            text = if (subBreedName.isBlank()) {
                stringResource(id = R.string.any_sub_breed)
            } else {
                subBreedName
            },
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
        breeds = Breeds(
            mapOf(
                Breed("Corgi") to SubBreeds(listOf("cardigan")),
                Breed("Dingo") to SubBreeds(listOf()),
                Breed("Hound") to SubBreeds(listOf("afghan", "basset"))
            )
        ),
        listState = rememberLazyListState(),
        onClickOpenImage = { _, _ -> }
    )
}

