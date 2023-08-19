// Developed by hedrax <Alhossien.waly@ejust.edu.eg>

package com.example.flightmlapplication.ui.pages.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flightmlapplication.ui.theme.transparent
import com.example.flightmlapplication.utils.Airport
import com.example.flightmlapplication.ui.theme.white


@Composable
fun DropDownEntry(
    modifier: Modifier = Modifier,
    list: Array<String> = arrayOf(),
    onClick: (String) -> Unit,
    title: String
) {

    val options = list.toMutableList()

    //special case when passing numbers
    if (title == "Number of passengers") {
        options.clear()
        for (i in 1..list[0].toInt())
            options.add(i.toString())
    }
    //states variables
   var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(options[0]) }


        Box(
            modifier = modifier
                .size(width = 360.dp, 95.dp)
                .padding(start = 30.dp, top = 15.dp)
                .clickable { expanded = !expanded }
        ) {
            Divider(
                color = white,
                thickness = 1.dp,
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .alpha(1f)
                    .padding(top = 60.dp)

            )
            Text(text = title, color = white)
            Row(modifier = modifier
                .fillMaxWidth()
                .padding(top = 25.dp),
                Arrangement.SpaceBetween
            ) {
                Text(text = selectedOption, fontSize = 22.sp, color = white)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier.background(transparent)
                    .width(330.dp)
            ) {
                options.forEach {
                    DropdownMenuItem(
                        text = { Text(text = it, fontSize = 22.sp) },
                        onClick = { selectedOption = it;expanded = false; onClick(selectedOption)},
                          modifier.background(transparent)
                              .height(IntrinsicSize.Min))
                }
            }
        }
}



@Composable
fun DropDownEntry(
    modifier: Modifier = Modifier,
    list: List<Airport>,
    onClick: (Airport) -> Unit,
    title: String
) {

    val options = list.toMutableList()

    //states variables
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(options[0]) }


    Box(
        modifier = modifier.size(width = 360.dp,95.dp)
            .padding(start = 30.dp, top = 15.dp)
            .clickable { expanded = !expanded }
    ) {
        Divider(
            color = white,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 4.dp)
                .alpha(1f)
                .padding(top = 60.dp)

        )
        Text(text = title, color = white)
        Row(modifier = modifier.fillMaxWidth()
            .padding(top = 25.dp),
            Arrangement.SpaceBetween
        ) {
            Text(text = selectedOption.airport, fontSize = 22.sp, color = white, maxLines = 1)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier.background(transparent)
                .width(330.dp)

        ) {

            options.forEach {
                DropdownMenuItem(
                    text = { Text(text = it.airport, fontSize = 22.sp, maxLines = 1) },
                    onClick = { selectedOption = it;expanded = false; onClick(selectedOption)},
                    modifier.background(transparent))
            }
        }
    }
}

