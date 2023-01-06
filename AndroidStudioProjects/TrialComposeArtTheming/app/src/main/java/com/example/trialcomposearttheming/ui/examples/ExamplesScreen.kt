package com.example.trialcomposearttheming.ui.examples

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import com.example.trialcomposearttheming.data.listImages
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trialcomposearttheming.R
import com.example.trialcomposearttheming.data.Images

@Composable
fun ExamplesScreen(
    @DrawableRes imageDisplayed: Int = R.drawable.ic_launcher_foreground,
    images: List<Images> = listImages,
    onAccountClick: (String) -> Unit = {}
) {

    LazyColumn(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 20.dp)
    ) {
        items(images) { image ->
            exampleCard(
                imageDisplayed = image.imageId,
                paintingName = image.name,
                modifier = Modifier.clickable {
                    image.name?.let { onAccountClick(it) }
                }
            )

        }
    }
}

@Composable
private fun exampleCard(
    @DrawableRes imageDisplayed: Int,
    paintingName: String?,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp),
        elevation = 5.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Image(
            painter = painterResource(id = imageDisplayed),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = paintingName ?: "Painting Name",
                color = MaterialTheme.colors.onSecondary,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 35.sp,
                modifier = Modifier.shadow(elevation = 4.dp)
            )
        }
    }
}