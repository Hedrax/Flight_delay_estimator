// Developed by hedrax <Alhossien.waly@ejust.edu.eg>

package com.example.flightmlapplication.utils

data class Result(
    val carrier:String,
    val origin:Airport,
    val startTime:String,
    val endTime:String,
    val dest:Airport,
    val price:Int,
    val travelTime:String,
    val estimatedDelay:Float,
)
