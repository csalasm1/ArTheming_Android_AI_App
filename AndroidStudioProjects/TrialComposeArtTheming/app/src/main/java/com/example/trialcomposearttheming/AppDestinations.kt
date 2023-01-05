package com.example.trialcomposearttheming

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

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

val appTabRowScreens = listOf(Home, Theming, Examples)