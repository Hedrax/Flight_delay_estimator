// Developed by hedrax <Alhossien.waly@ejust.edu.eg>

package com.example.flightmlapplication.ui.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.flightmlapplication.ViewModel
import com.example.flightmlapplication.ui.pages.navigation.AppNavHost

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun App(viewModel: ViewModel) {
    val navController = rememberNavController()
    viewModel.setNavController(navHostController = navController)
    AppNavHost(navController, viewModel)
}