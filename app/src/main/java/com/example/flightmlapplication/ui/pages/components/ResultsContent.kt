// Developed by hedrax <Alhossien.waly@ejust.edu.eg>

package com.example.flightmlapplication.ui.pages.components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flightmlapplication.R
import com.example.flightmlapplication.ViewModel
import com.example.flightmlapplication.ui.theme.green
import com.example.flightmlapplication.ui.theme.grey01
import com.example.flightmlapplication.ui.theme.red
import com.example.flightmlapplication.utils.Result

@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ResultsContent(modifier: Modifier, viewModel:ViewModel){
        Column(
            modifier
                .background(grey01)
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp)){
        Row (modifier, Arrangement.spacedBy(175.dp)){
            Text(
                "18 Flights Found",
                fontSize = 16.sp,
                modifier = modifier.padding(top = 15.dp))
            IconButton(onClick = { /*Nothing in particular*/}) {
                Icon(
                    painter = painterResource(id = R.drawable.equalizer),
                    contentDescription = "Filter", modifier = modifier
                        .alpha(0.8f)
                        .size(30.dp)
                )
            }
        }
        LazyColumn(content = {
            this.items(viewModel.results.size) {
                    Item(
                        modifier = modifier,
                        result = viewModel.results[it])
                Spacer(modifier = modifier.size(20.dp))
            }
        })
    }
}


@Composable
fun Item (modifier :Modifier = Modifier, result: Result){
    val lightBlack = Color(0xFF181818)
    val delayText = if (result.estimatedDelay < 0) "${result.estimatedDelay} mins" else "+${result.estimatedDelay} mins"
    val color = if (result.estimatedDelay < 0) green else red
    Surface(
        modifier
            .clip(MaterialTheme.shapes.large)
            .clickable { /*Nothing in specific*/ }
    ){
        //using box composable to overlay each-other
        Box(modifier = Modifier.fillMaxSize()) {

                Column (Modifier.offset(15.dp,10.dp), horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = result.origin.shortCut,  fontSize = 45.sp, color = lightBlack, fontWeight = FontWeight(500))
                    Text(text = result.startTime, fontSize = 12.sp, maxLines = 1, color = lightBlack)
                }

                Image(
                    painter = painterResource(id = R.drawable.black_arrow),
                    contentDescription = "Black Arrow",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(top = 30.dp)
                        .offset(90.dp, 0.dp),
                    Alignment.TopCenter
                )


                Column (Modifier.offset(235.dp,10.dp), horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = result.dest.shortCut, fontSize = 45.sp, fontWeight = FontWeight(500), color = lightBlack)
                    Text(text = result.endTime, fontSize = 12.sp, maxLines = 1, color = lightBlack)
                }

                Column (modifier = Modifier.offset(23.dp,90.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Est. Delay", fontWeight = FontWeight(450), color = lightBlack)
                    Text(text = delayText, modifier, color = color)
                }
                    Text(text = result.carrier, fontSize = 16.sp, modifier = Modifier
                        .width(120.dp)
                        .offset(110.dp, 65.dp), textAlign = TextAlign.Center, maxLines = 2, color = lightBlack)
                    Text(text = result.travelTime, color = Color.Gray,
                        modifier = modifier.offset(140.dp,110.dp))
                Text(text = "$${result.price}", fontWeight = FontWeight.W400, fontSize = 25.sp,
                        modifier = modifier.offset(250.dp,95.dp), color = Color(0xFF202020))
        }
    }
}