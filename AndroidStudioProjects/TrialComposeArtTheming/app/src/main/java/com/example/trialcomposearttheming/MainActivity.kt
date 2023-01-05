package com.example.trialcomposearttheming

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.trialcomposearttheming.data.listImages
import com.example.trialcomposearttheming.ui.components.BottomTabRow
import com.example.trialcomposearttheming.ui.components.DynamicThemePrimaryColorsFromImage
import com.example.trialcomposearttheming.ui.components.TopBarRow
import com.example.trialcomposearttheming.ui.components.rememberDominantColorState
import com.example.trialcomposearttheming.ui.theme.TrialComposeArtThemingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrialComposeArtThemingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RallyApp()
                }
            }
        }
    }
}

@Composable
fun RallyApp() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen =
        appTabRowScreens.find { it.route == currentDestination?.route } ?: Home
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val title by remember { mutableStateOf(currentScreen.route) }
    val values = (0.. 3).toList()
    val someBoolean = remember { mutableStateOf(false) }
    var random = values.random()
    val imageData = listImages[random]

    val surfaceColor = imageData.themingColors.primaryColor
    val dominantColorState = rememberDominantColorState(surfaceColor)


    DynamicThemePrimaryColorsFromImage(dominantColorState) {

        // When the selected image url changes, call updateColorsFromImageUrl() or reset()
        LaunchedEffect(imageData) {
            if (imageData != null) {
                dominantColorState.updateColorsFromImage(imageData.themingColors)
            } else {
                dominantColorState.reset()
            }
        }
        Scaffold(
            bottomBar = {
                BottomTabRow(
                    allScreens = appTabRowScreens,
                    onTabSelected = { newScreen ->
                        navController.navigateSingleTopTo(newScreen.route)
                    },
                    currentScreen = currentScreen
                )
            },
            topBar = {
                TopBarRow(
                    navController = navController,
                    scaffoldState = scaffoldState,
                    scope = scope,
                    currentScreen = currentScreen
                )
            }

        ) { innerPadding ->
            AppNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                imageDisplayed = imageData.imageId,
                onClick = { someBoolean.value = !someBoolean.value }
            )
        }
    }
}
