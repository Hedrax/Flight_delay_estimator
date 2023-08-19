// Developed by hedrax <Alhossien.waly@ejust.edu.eg>

//Copyright (c) 2023 Hedrax
//
//Permission is hereby granted, free of charge, to any person obtaining a copy
//of this software and associated documentation files (the "Software"), to deal
//in the Software without restriction, including without limitation the rights
//to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//copies of the Software, and to permit persons to whom the Software is
//furnished to do so, subject to the following conditions:

package com.example.flightmlapplication

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.flightmlapplication.ui.pages.App
import com.example.flightmlapplication.ui.theme.FlightMLApplicationTheme

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val viewModel = ViewModel(this)
        super.onCreate(savedInstanceState)
        setContent {
            FlightMLApplicationTheme {
                    App(
                        viewModel = viewModel
                    )
            }
        }
        viewModel.dataReady.observe(this){
            if (it) viewModel.navigateToResult()
        }
    }
}
