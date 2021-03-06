package com.zarinraim.dogsearch.feature.list

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.Divider
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zarinraim.dogsearch.R
import com.zarinraim.dogsearch.domain.model.Breed
import com.zarinraim.dogsearch.domain.model.SubBreeds
import org.koin.androidx.compose.viewModel

@ExperimentalMaterialApi
@Composable
fun BreedsListScreen(
    onClickOpenImage: (String, String) -> Unit = { _, _ -> }
) {
    val viewModel: BreedsListViewModel by viewModel()
    val state = viewModel.state.value

    val listState = rememberLazyListState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        BreedsList(
            breeds = state.breeds,
            listState = listState,
            onClickOpenImage = onClickOpenImage
        )

        if (state.error.isNotBlank()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 20.dp)
                )

                OutlinedButton(
                    onClick = {
                        viewModel.refresh()
                    }) {
                    Text(
                        text = "Retry",
                        style = MaterialTheme.typography.button
                    )
                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun BreedsList(
    breeds: Map<Breed, SubBreeds>,
    listState: LazyListState,
    onClickOpenImage: (String, String) -> Unit
) {
    val mainBreedsList = breeds.keys.toList()

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
                subBreeds = breeds[breed],
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
        targetValue = if (expanded.value) ROTATION_ANGLE else 0f
    )

    val hasSubBreeds = subBreeds?.list?.isNotEmpty() ?: false

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
                        .weight(TEXT_WEIGHT)
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

        if (subBreeds != null && hasSubBreeds && expanded.value) {
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
                .weight(TEXT_WEIGHT)
        )

        IconButton(
            modifier = Modifier
                .weight(ICON_BUTTON_WEIGHT)
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
            Breed("Corgi") to SubBreeds(listOf("cardigan")),
            Breed("Dingo") to SubBreeds(listOf()),
            Breed("Hound") to SubBreeds(listOf("afghan", "basset"))
        ),
        listState = rememberLazyListState(),
        onClickOpenImage = { _, _ -> }
    )
}

private const val TEXT_WEIGHT = 6f
private const val ICON_BUTTON_WEIGHT = 1f
private const val ROTATION_ANGLE = 1f
