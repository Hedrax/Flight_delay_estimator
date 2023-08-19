// Developed by hedrax <Alhossien.waly@ejust.edu.eg>

package com.example.flightmlapplication.ui.pages.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.flightmlapplication.ViewModel
import com.example.flightmlapplication.ui.pages.ResultsPage
import com.example.flightmlapplication.ui.pages.SelectionPage

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(navController: NavHostController, viewModel: ViewModel) {
    NavHost(
        navController = navController,
        startDestination = Pages.SelectionPage.toString()
    ) {
        composable(Pages.SelectionPage.toString()) {

            SelectionPage(modifier = Modifier, viewModel = viewModel)
        }
        composable( Pages.ResultsPage.toString()) {
            ResultsPage(modifier = Modifier, viewModel = viewModel)
        }
    }
}
enum class Pages{
    SelectionPage, ResultsPage
}