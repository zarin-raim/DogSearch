package com.zarinraim.dogsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zarinraim.dogsearch.ui.theme.DogSearchTheme

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController


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


