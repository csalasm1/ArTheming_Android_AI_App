package com.example.trialcomposearttheming.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.trialcomposearttheming.BottomDestination
import com.example.trialcomposearttheming.Examples
import com.example.trialcomposearttheming.Home
import java.util.Locale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
internal fun TopBarRow(
    scaffoldState: ScaffoldState,
    scope: CoroutineScope,
    navController: NavController,
    currentScreen: BottomDestination
) {
    val text = currentScreen.route
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.99f),
        contentColor = MaterialTheme.colors.onPrimary.copy(alpha = 0.99f),
        navigationIcon = {
            IconButton(
                onClick = {
                    if (navController.previousBackStackEntry != null) {
                        navController.navigateUp()
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Button",
                )
            }
        },
        actions = {
            IconButton(onClick = {
                navController.navigate(Examples.route)
            }) {
                Icon(
                    imageVector = Icons.Filled.List,
                    contentDescription = "List button",
                )
            }

            IconButton(onClick = { navController.navigate(Home.route)
             }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add button",
                    tint = MaterialTheme.colors.onPrimary.copy(alpha = 0.99f)
                )
            }
        },
        title = {
            Text(text.uppercase(Locale.getDefault()))
        }
    )
}