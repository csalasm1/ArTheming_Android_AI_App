package com.example.trialcomposearttheming.data

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.example.trialcomposearttheming.R

@Immutable
data class ThemingColors(
    val primaryColor: Color,
    val secondaryColor: Color,
    val tertiaryColor: Color?
)

@Immutable
data class Images(
    @DrawableRes val imageId: Int,
    val themingColors: ThemingColors,
    val name: String? = null,
)

val listImages = listOf(
    Images(
        imageId = R.drawable.paisaje,
        name = "Sunset",
        themingColors = ThemingColors(
            primaryColor = Color(0xfff444B80),
            secondaryColor = Color(0xfffA389A9),
            tertiaryColor = Color(0xfffDFB5AC)
        ),
    ),
    Images(
        imageId = R.drawable.catscream,
        name = "Cat Scream",
        themingColors = ThemingColors(
            primaryColor = Color(0xfffC48156),
            secondaryColor = Color(0xfff7E766A),
            tertiaryColor = Color(0xfff444953)
        ),
    ),
    Images(
        imageId = R.drawable.proteus1,
        name = "Clustering Sample",
        themingColors = ThemingColors(
            primaryColor = Color(0xfff5DAA5B),
            secondaryColor = Color(0xfff93CBDE),
            tertiaryColor = Color(0xfffE8D649)
        ),
    ),
    Images(
        imageId = R.drawable.starry_night,
        name = "Starry Night",
        themingColors = ThemingColors(
            primaryColor = Color(0xfff313649),
            secondaryColor = Color(0xfff5B6684),
            tertiaryColor = Color(0xfffB4B7AE)
        ),
    )
)