package com.example.trialcomposearttheming

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface BottomDestination {
    val icon: ImageVector
    val route: String
}

object Home: BottomDestination {
    override val icon = Icons.Filled.Home
    override val route = "home"
}

object Theming: BottomDestination {
    override val icon = Icons.Filled.Create
    override val route = "theming"
}

object Examples: BottomDestination {
    override val icon = Icons.Filled.List
    override val route = "examples"
}

object ThemingExamples: BottomDestination {
    override val icon = Icons.Filled.Create
    override val route = "single_account"
    const val accountTypeArg = "account_type"
    val routeWithArgs = "$route/{$accountTypeArg}"
    val arguments = listOf(
        navArgument(accountTypeArg) { type = NavType.StringType }
    )


}

val appTabRowScreens = listOf(Home, Theming, Examples)