// Developed by hedrax <Alhossien.waly@ejust.edu.eg>

package com.example.flightmlapplication.ui.pages.components

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flightmlapplication.ui.theme.white
import java.util.Calendar
import java.util.Date


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DataPicker(
    modifier: Modifier = Modifier,
    date: String,
    onClick: (String, Int, Int) -> Unit) {

    // Fetching the Local Context
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf(date) }

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _, year, month, dayOfMonth->
            mDate.value = "$dayOfMonth/${month+1}/$year"
        }, mYear, mMonth, mDay
    )

    Box(
        modifier = modifier.size(width = 360.dp,95.dp)
            .padding(start = 30.dp, top = 15.dp)
            .clickable { mDatePickerDialog.show();onClick(mDate.value, mMonth, mDay) }
    ) {
        Divider(
            color = white,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 4.dp)
                .alpha(1f)
                .padding(top = 60.dp)

        )
        Text(text = "Date", color = white)
        Row(modifier = modifier.fillMaxWidth()
            .padding(top = 25.dp),
            Arrangement.SpaceBetween
        ) {
            Text(text = mDate.value, fontSize = 22.sp, color = white)
        }
    }


}
