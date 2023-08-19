// Developed by hedrax <Alhossien.waly@ejust.edu.eg>

package com.example.flightmlapplication.ui.pages.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.flightmlapplication.R
import com.example.flightmlapplication.ui.theme.Purple40

@Composable
fun ActionButton(modifier: Modifier = Modifier, onClick:()->Unit)
{
    Box(
        modifier.clip(CircleShape)
        .size(80.dp) // Set the width and height to the same value for a circular shape
        .background(Purple40)
        .clickable {onClick()}
        , Alignment.Center
    ){
        Image(painter = painterResource(R.drawable.plane), contentDescription = "Home", Modifier.size(52.dp))
    }
}