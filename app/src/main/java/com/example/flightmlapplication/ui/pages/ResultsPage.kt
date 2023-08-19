// Developed by hedrax <Alhossien.waly@ejust.edu.eg>

package com.example.flightmlapplication.ui.pages

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.flightmlapplication.R
import com.example.flightmlapplication.ViewModel
import com.example.flightmlapplication.ui.pages.components.HeaderResult
import com.example.flightmlapplication.ui.pages.components.ResultsContent
import com.example.flightmlapplication.ui.theme.transparent

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ResultsPage(modifier: Modifier, viewModel: ViewModel){
    Column {
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
                IconButton(
                    //navigation when it happens
                    onClick = { viewModel.navigateBack() },
                    Modifier
                        .background(transparent)
                        .padding(top = 12.dp, start = 5.dp)
                ) {
                    Image(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = ""
                    )
                }
                Column {
                    HeaderResult(
                        viewModel = viewModel,
                        modifier = modifier
                            .background(color = Color(0x220000FF))
                    )

                    ResultsContent(modifier = modifier, viewModel = viewModel)
                }
            }
        }
    }
}
