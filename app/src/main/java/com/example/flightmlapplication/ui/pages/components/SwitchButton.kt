// Developed by hedrax <Alhossien.waly@ejust.edu.eg>

package com.example.flightmlapplication.ui.pages.components


import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flightmlapplication.R
import com.example.flightmlapplication.TripType
import com.example.flightmlapplication.ViewModel
import com.example.flightmlapplication.ui.theme.purple01
import com.example.flightmlapplication.ui.theme.transparent
import com.example.flightmlapplication.ui.theme.white


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SwitchButton(modifier: Modifier = Modifier, viewModel: ViewModel){
    val type by remember { viewModel.tripType}
    fun onClick(value:String){
        viewModel.setTripType(value)
    }
        Row (modifier = modifier
            .background(color = transparent, shape = MaterialTheme.shapes.small)
            .border(width = 1.dp, color = white, shape = MaterialTheme.shapes.large)
            .size(width = 340.dp, height = 50.dp),
            Arrangement.SpaceBetween
            ){
            FlightTypeButton(
                selected = type,
                text = stringResource(R.string.one_way),
                onClick = ::onClick
            )
            FlightTypeButton(
                selected = type,
                text = stringResource(R.string.round_trip),
                onClick = ::onClick
            )
        }

}


@SuppressLint("SuspiciousIndentation")
@Composable
fun FlightTypeButton(
    modifier: Modifier = Modifier,
    selected: TripType,
    text: String,
    onClick:(String)->Unit
){
    val triggered = selected.toString()[0] != text[0]
    val textColor  = if (!triggered) purple01 else white

    //applying animation in the background
    val backGroundColor = animateColorAsState(targetValue = if (!triggered) white else transparent,
        label = "Switch Button Color",
        animationSpec = tween(
            durationMillis = 300,
            delayMillis = 50,
            easing = FastOutSlowInEasing))

        Box(
            modifier = modifier
                .clickable { onClick(text) }
                .width(170.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(color =  backGroundColor.value)
            , Alignment.Center
        ) {
            Text(text = text,
                color = textColor,
                modifier =
                Modifier
                    .padding(vertical = 9.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight(500)
                )
        }

}

