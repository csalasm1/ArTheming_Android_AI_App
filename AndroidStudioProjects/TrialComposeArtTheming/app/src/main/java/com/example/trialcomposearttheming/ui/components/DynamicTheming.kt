package com.example.trialcomposearttheming.ui.components

import android.content.Context
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.trialcomposearttheming.data.ThemingColors

@Composable
fun DynamicThemePrimaryColorsFromImage(
    dominantColorState: DominantColorState = rememberDominantColorState(),
    content: @Composable () -> Unit
) {
    val colors = MaterialTheme.colors.copy(
        primary = animateColorAsState(
            dominantColorState.color,
            spring(stiffness = Spring.StiffnessLow)
        ).value,
        onPrimary = animateColorAsState(
            dominantColorState.secondaryColor,
            spring(stiffness = Spring.StiffnessLow)
        ).value,
        secondary = animateColorAsState(
            dominantColorState.thirdColor,
            spring(stiffness = Spring.StiffnessLow)
        ).value
    )
    MaterialTheme(colors = colors, content = content)
}

@Composable
fun rememberDominantColorState(
    //context: Context = LocalContext.current,
    defaultColor: Color = MaterialTheme.colors.primary,
    defaultOnColor: Color = MaterialTheme.colors.onPrimary,
    defaultThirdColor: Color = MaterialTheme.colors.secondary,
): DominantColorState = remember {
    DominantColorState(defaultColor, defaultOnColor, defaultThirdColor)
}

@Stable
class DominantColorState(
    //private val context: Context,
    private val defaultColor: Color,
    private val defaultSecondaryColor: Color,
    private val defaultTertiaryColor: Color
) {
    var color by mutableStateOf(defaultColor)
        private set
    var secondaryColor by mutableStateOf(defaultSecondaryColor)
        private set
    var thirdColor by mutableStateOf(defaultTertiaryColor)
        private set

    suspend fun updateColorsFromImage(result: ThemingColors?) {
        color = (result?.primaryColor ?: defaultColor) as Color
        secondaryColor = (result?.secondaryColor ?: defaultSecondaryColor) as Color
        thirdColor = (result?.tertiaryColor ?: defaultTertiaryColor) as Color
    }


    fun reset() {
        color = defaultColor
        secondaryColor = defaultSecondaryColor
        thirdColor = defaultTertiaryColor
    }
}


//Color.rgb(red, green, blue)