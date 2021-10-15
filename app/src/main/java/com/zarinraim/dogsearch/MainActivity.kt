package com.zarinraim.dogsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.zarinraim.dogsearch.ui.theme.DogSearchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogSearchTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DogBreedsList()
                }
            }
        }
    }
}

@Composable
fun DogBreedsList(viewModel: DogBreedsListModel = DogBreedsListModel()) {
    val dogBreeds = viewModel.dogBreeds.value
    Text(dogBreeds.size.toString())
}

@Preview
@Composable
fun PreviewDogBreedList() {
    DogBreedsList()
}