package com.zarinraim.dogsearch.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.zarinraim.dogsearch.presentation.navigation.SetupNavGraph
import com.zarinraim.dogsearch.presentation.ui.theme.DogSearchTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    @ExperimentalCoilApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogSearchTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}


