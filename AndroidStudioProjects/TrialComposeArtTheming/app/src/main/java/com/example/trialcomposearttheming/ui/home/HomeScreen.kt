package com.example.trialcomposearttheming.ui.home

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.trialcomposearttheming.R

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HomeScreen(
    onClickSeeAllAccounts: () -> Unit = {},
    onClickSeeAllBills: () -> Unit = {},
    @DrawableRes imageDisplayed: Int = R.drawable.ic_launcher_foreground,
    imageUri: Uri?
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)) {

        Text(text = "Artheming",
        color = MaterialTheme.colors.onSecondary,
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.padding(20.dp))

        Image(painter = painterResource(id = imageDisplayed),
            contentDescription = null,
        contentScale = ContentScale.Fit)

        Spacer(modifier = Modifier.padding(20.dp))

        Button(
            onClick = onClickSeeAllAccounts ,
            modifier = Modifier.fillMaxWidth(0.7f),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary,
                contentColor = MaterialTheme.colors.onPrimary),
        ) {
            Text(text = "Choose a different Random Painting")
        }

        Spacer(modifier = Modifier.padding(20.dp))

        Button(
            onClick = onClickSeeAllBills,
            modifier = Modifier.fillMaxWidth(0.7f),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary,
                contentColor = MaterialTheme.colors.onPrimary),
        ) {
            Text(text = "Choose from gallery")
        }

        Spacer(modifier = Modifier.padding(20.dp))

        imageUri?.let {
            Image(painter = rememberImagePainter(imageUri),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    }
}