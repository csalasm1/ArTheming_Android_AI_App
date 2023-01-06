package com.example.trialcomposearttheming.ui.themingdetails

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trialcomposearttheming.data.Images
import com.example.trialcomposearttheming.data.listImages
import com.example.trialcomposearttheming.data.getAccount

const val MinContrastOfPrimaryVsSurface = 3f
val OptionCardPadding = 10.dp

@Composable
fun ThemingScreen(
    accountType: String? = listImages.first().name
) {
    val account = remember(accountType) { getAccount(accountType) }
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 20.dp)
    ) {


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp),
            elevation = 5.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Image(
                painter = painterResource(id = account.imageId),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                account.name?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colors.onSecondary,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 35.sp,
                        modifier = Modifier.shadow(elevation = 4.dp)
                    )
                }
            }
        }


        Spacer(modifier = Modifier.padding(20.dp))

        val formatOptions = listOf("Hex", "RGB")
        val colorOptions = listOf("Regular", "Mono")

        var selectedItem by remember {
            mutableStateOf(formatOptions[0])
        }

        var selectedColor by remember {
            mutableStateOf(colorOptions[0])
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .wrapContentHeight()
                .selectableGroup()
        ) {
            Card(
                modifier = Modifier
                    .width(100.dp)
                    .padding(vertical = OptionCardPadding),
                shape = RoundedCornerShape(4.dp),
                elevation = 3.dp
            ) {
                Text(text = "Format", textAlign = TextAlign.Center)
            }
            formatOptions.forEach { label ->
                val color = if (selectedItem == label) MaterialTheme.colors.secondary else {
                    MaterialTheme.colors.background
                }
                val onColor = if (selectedItem == label) MaterialTheme.colors.onPrimary else {
                    MaterialTheme.colors.onBackground
                }

                Card(
                    modifier = Modifier
                        .width(50.dp)
                        .padding(vertical = OptionCardPadding)
                        .selectable(
                            selected = (selectedItem == label),
                        ) { selectedItem = label },
                    shape = RoundedCornerShape(4.dp),
                    elevation = 3.dp,
                    backgroundColor = color
                ) {
                    Text(text = label, textAlign = TextAlign.Center,
                        color = onColor)
                }
            }
        }

        Spacer(modifier = Modifier.padding(10.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .wrapContentHeight()
        ) {
            Card(
                modifier = Modifier
                    .width(100.dp)
                    .padding(vertical = OptionCardPadding),
                shape = RoundedCornerShape(4.dp),
                elevation = 3.dp
            ) {
                Text(text = "Color", textAlign = TextAlign.Center)
            }
            colorOptions.forEach { label ->
                val color = if (selectedColor == label) MaterialTheme.colors.secondary else {
                    MaterialTheme.colors.background
                }
                val onColor = if (selectedColor == label) MaterialTheme.colors.onPrimary else {
                    MaterialTheme.colors.onBackground
                }
                Card(
                    modifier = Modifier
                        .width(60.dp)
                        .padding(vertical = OptionCardPadding)
                        .selectable(
                            selected = (selectedColor == label),
                        ) { selectedColor = label },
                    shape = RoundedCornerShape(4.dp),
                    elevation = 3.dp,
                    backgroundColor = color
                ) {
                    Text(text = label, textAlign = TextAlign.Center,
                        color = onColor)
                }
            }
        }

        Spacer(modifier = Modifier.padding(10.dp))

        val listOfColors = listOf(
            MaterialTheme.colors.primary,
            MaterialTheme.colors.onPrimary,
            MaterialTheme.colors.secondary
            )

        listOfColors.forEach { color ->
            colorReference(color = color, format = selectedItem)
        }

        /*val rgbColors = listOf(Color.Blue.red, )
        Text("$rgbValue",
            fontSize = 10.sp)

        val hex = rgbToHex(Color.Gray)
        Text("$hex",
            fontSize = 10.sp)
        */
    }
}

@Composable
private fun colorReference(
    color: Color,
    format: String) {
    when (format) {
        "Hex" -> {
            DisplayColors(
                colorShowed = color,
                colorReference = rgbToHex(color),
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        "RGB" -> {
            DisplayColors(
                colorShowed = color,
                colorReference = rgbText(color),
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

private fun rgbToHex(color: Color) : String {
    val rgbColors = listOf(
        color.red,
        color.green,
        color.blue
    )
    var hex = ""

    rgbColors.forEach { item ->
        hex += Integer.toHexString((item*255.0f).toInt())
        println(hex)
    }

    return hex
}

private fun rgbDisplay(color: Color): List<Int> {
    return listOf(
        (color.red * 255.0f).toInt(),
        (color.green * 255.0f).toInt(),
        (color.blue * 255.0f).toInt()
    )
}

private fun rgbText(color: Color): String {
    val rgbList = listOf("R:", " G:", " B:")
    val rgbInts = rgbDisplay(color)
    var colorString = ""
    for (i in 0..2) {
        colorString += rgbList[i] + rgbInts[i]
    }
    return colorString
}

@Composable
private fun DisplayColors(
    colorShowed: Color,
    colorReference: String,
    modifier: Modifier = Modifier ) {
    Row(
        modifier = modifier.fillMaxWidth()

    ) {
        Card(
            modifier = Modifier
                .width(180.dp)
                .height(30.dp),
            elevation = 1.dp,
            backgroundColor = colorShowed
        ) {
        }

        Spacer(modifier = Modifier.padding(horizontal = 20.dp))

        Card(
            modifier = Modifier
                .width(120.dp)
                .height(30.dp),
            elevation = 2.dp,
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Text(text = colorReference,
                fontSize = 12.sp,
                modifier = Modifier.padding(5.dp))
        }

    }
}