// Developed by hedrax <Alhossien.waly@ejust.edu.eg>

//Copyright (c) 2023 Hedrax
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:

package com.example.flightmlapplication.ui.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flightmlapplication.R
import com.example.flightmlapplication.ViewModel
import com.example.flightmlapplication.ui.pages.components.ActionButton
import com.example.flightmlapplication.ui.pages.components.DataPicker
import com.example.flightmlapplication.ui.pages.components.DropDownEntry
import com.example.flightmlapplication.ui.pages.components.SwitchButton
import com.example.flightmlapplication.ui.theme.transparent
import com.example.flightmlapplication.ui.theme.white


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SelectionPage(modifier: Modifier, viewModel: ViewModel){
    val imageResource = painterResource(id = R.drawable.plane_wing_background)

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background image with overlay
        Box(
            modifier = Modifier
        ) {
            Image(
                painter = imageResource,
                contentDescription = null,
                modifier = Modifier
                    .graphicsLayer(
                        scaleX = 1.2f,
                        scaleY = 1.4f
                    )
            )
            Box(
                modifier = modifier
                    .background(color = Color(0x220000FF))
                    .fillMaxSize()
            ) {
                OverLayColumn(modifier = modifier, viewModel)
            }
            ActionButton(
                modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 34.dp, end = 15.dp)
                , onClick = {viewModel.showResults(); }
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OverLayColumn(modifier: Modifier, viewModel: ViewModel){

    val showLoading by remember {viewModel.showLoading}
    Column(modifier.fillMaxSize()) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 40.dp, bottom = 15.dp),
            text = "Book your flight",
            color = white,
            fontFamily = FontFamily.SansSerif,
            fontSize = 60.sp,
            lineHeight = 65.sp,
            fontWeight = FontWeight.ExtraBold
        )

        SwitchButton(modifier.padding(start = 25.dp), viewModel)
        DropDownEntry(modifier, viewModel.airports, {viewModel.setOrigin(it)}, "From")
        DropDownEntry(modifier, viewModel.airports, {viewModel.setDestination(it)},"To")
        DataPicker(date = viewModel.getDate(), onClick = {it1,it2,it3 -> viewModel.setDate(it1,it2,it3)})
        DropDownEntry(modifier, arrayOf(viewModel.getMaxPassengers().toString()), {viewModel.setPassengersNum(it)}, "Number of passengers")
        DropDownEntry(modifier, viewModel.classes, {viewModel.setClass(it)}, "Class")

    }
    if (showLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(transparent)
        ) {
            CircularProgressIndicator(strokeWidth = 40.dp, modifier = Modifier.size(60.dp))
        }
    }
}