package com.example.trialcomposearttheming

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.trialcomposearttheming.ui.examples.ExamplesScreen
import com.example.trialcomposearttheming.ui.home.HomeScreen
import com.example.trialcomposearttheming.ui.themingdetails.ThemingScreen


@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    @DrawableRes imageDisplayed: Int,
    onClick: () -> Unit,
) {

    var selectImage by remember {
        mutableStateOf<Uri?>(null)
    }
    val galleryLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
                selectImage = uri }

    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = modifier
    ) {
        composable(route = Home.route) {
            HomeScreen(
                onClickSeeAllAccounts = onClick,
                onClickSeeAllBills = { galleryLauncher.launch("image/*") },
                imageDisplayed = imageDisplayed,
                imageUri = selectImage)
        }
        composable(route = Theming.route) {
            ThemingScreen(
                imageDisplayed = imageDisplayed
            )
        }
        composable(route = Examples.route) {
            ExamplesScreen()
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
private fun NavHostController.navigateToSingleAccount(accountType: String) {
    this.navigateSingleTopTo("${Theming.route}/$accountType")
}