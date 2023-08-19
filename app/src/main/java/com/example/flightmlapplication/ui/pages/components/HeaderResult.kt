// Developed by hedrax <Alhossien.waly@ejust.edu.eg>

package com.example.flightmlapplication.ui.pages.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flightmlapplication.R
import com.example.flightmlapplication.ViewModel
import com.example.flightmlapplication.ui.theme.white

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HeaderResult(modifier: Modifier = Modifier, viewModel: ViewModel){
    Box(modifier = modifier.fillMaxWidth())
    {
        Column {

            Box (modifier = Modifier.padding(top = 40.dp, start = 30.dp, bottom = 20.dp)){
                Column (Modifier, verticalArrangement = Arrangement.Center){
                 Text(text = viewModel.getOrigin().shortCut, color = white, fontSize = 54.sp, fontWeight = FontWeight(500))
                    Text(
                        text = viewModel.getOrigin().airport,
                        color = white,
                        fontSize = 12.sp,
                        modifier = Modifier.width(100.dp), maxLines = 1)
                }
                Image(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "Arrow",
                    modifier = Modifier
                        .size(120.dp)
                        .padding(top = 30.dp)
                        .offset(105.dp),
                    Alignment.TopCenter
                    )
                Column (Modifier.offset(220.dp), horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = viewModel.getDestination().shortCut, color = white, fontSize = 54.sp, fontWeight = FontWeight(500))
                    Text(
                        text = viewModel.getDestination().airport,
                        color = white,
                        fontSize = 12.sp,
                        modifier = Modifier.offset(0.dp,-(5).dp).width(100.dp),
                        maxLines = 1)
                }
            }
            Row (modifier = Modifier
                .padding(start = 35.dp, bottom = 17.dp)
                .offset(0.dp, (-30).dp), Arrangement.spacedBy(30.dp)) {
                val passengersCount = viewModel.getPassengersNum()
                Text(text = viewModel.getDate(), color = white)
                Text(text = "$passengersCount Passenger", color = white)
                Text(text = "economy", color = white)
            }
        }
    }
}