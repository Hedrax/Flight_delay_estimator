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

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.flightmlapplication.model_caller.PythonRunner
import com.example.flightmlapplication.ui.pages.navigation.Pages
import com.example.flightmlapplication.utils.Airport
import com.example.flightmlapplication.utils.InputData
import com.example.flightmlapplication.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random


@RequiresApi(Build.VERSION_CODES.O)
class ViewModel(val context: Context):ViewModel() {
    var showLoading = mutableStateOf(false)

    //Machine Learning interaction gate...
    private val pythonRunner = PythonRunner(context)

    //importing constants arrays of real data names
    private val temp: Array<String> = context.resources.getStringArray(R.array.Airports_dest)
    private val airportsShortcut: Array<String> = context.resources.getStringArray(R.array.AirportsShortCuts_dest)
    private val carriers: Array<String> = context.resources.getStringArray(R.array.Carriers)
    val classes: Array<String> = context.resources.getStringArray(R.array.Classes)

    val airports = mutableListOf<Airport>()

    private var navController:NavHostController? = null

    //initializing key variables
    private var _fromCity = Airport(temp[0],airportsShortcut[0])
    private var _toCity = Airport(temp[0],airportsShortcut[0])

    private var _date = getCurrentDate()
    private var day = 1
    private var month = 1

    private var _class = classes[0]

    //state to sync with the switch button ui
    var tripType = mutableStateOf( TripType.OneWay)

    private var _passengers = 1
    var results: MutableList<Result> = mutableListOf()

    private var time1String = ""
    private var time2String = ""
    private var duration = ""

    val dataReady = MutableLiveData<Boolean>(false)
    //float typed times for prediction
    private var time1 = 0.0
    private var time2 = 0.0

    init {
        for (i in temp.indices)
            airports.add(Airport(temp[i], airportsShortcut[i]))
    }

    //get a randomized start time float 0.0:24.0,
    //get a randomized duration float 1.0:3.0

    private fun randomizeTime(){
        val tempTime1 = Random.nextDouble(0.0, 24.0)
        val tempDuration =  Random.nextDouble(1.0, 3.0)
        var tempTime2 = tempTime1 + tempDuration
         if (tempTime2 >= 24)tempTime2 -= 24
        time1String = convertFloatToTime(tempTime1, false)
        time2String = convertFloatToTime(tempTime2, false)
        duration = convertFloatToTime(tempDuration, true)
        //model takes values in 2400.0 form
        time1 = tempTime1*100
        time2 = tempTime2*100

    }

    private fun convertFloatToTime(double: Double, duration:Boolean):String{
        val hour:Int = if (!duration) double.toInt()/2 else double.toInt()
        //getting the decimal mins and converting them from 100 scale to 60 scale
        val mins:Int = (((double*100)%100)*60/100).toInt()

        var timeType = ":00"
        if (!duration)
            timeType = if(double >= 12) "PM" else "AM"

        return "${hour}:${if(mins < 10) "0$mins" else mins}${timeType}"
    }

    private fun appendResults(){
        for( i in carriers.indices){
            randomizeTime()
            results.add(
                Result(
                    carriers[i],
                    _fromCity,
                    time1String,
                    time2String,
                    _toCity,
                    getPrice(),
                    duration,
                    predict()
            ))
        }
    }
    private fun getPrice():Int{
        return Random.nextInt(120,250)
    }
    //getters and setters for each key var
    fun setDestination(value : Airport){
        _toCity = value
    }
    fun getDestination(): Airport = _toCity

    fun setOrigin(value : Airport){
     _fromCity = value
    }
    fun getOrigin(): Airport = _fromCity

    fun setDate(value :String, mMonth :Int, mDay :Int ){
        _date = value
        day = mDay
        month = mMonth
    }
    fun getDate():String = _date

    fun setClass(value :String){
        _class = value
    }

    fun setPassengersNum(value :String){
        _passengers = value.toInt()
    }
    fun getPassengersNum():String = _passengers.toString()

    fun setTripType(value: String) {
        if (value == "Round Trip")
            tripType.value = TripType.RoundTrip
        else
            tripType.value = TripType.OneWay
    }

    //used for assigning the nav controller after declaration of the ViewModel
    fun setNavController(navHostController: NavHostController){
        navController = navHostController
    }

    fun getMaxPassengers() = passengersMaxToBePicked

    //making sure that we're not traveling to the same city lmao
    private fun validate() : Boolean{
        if (_fromCity == _toCity)
            return false
        return true
    }

    fun setShowLoading(boolean: Boolean){
        showLoading.value = boolean
    }


    //make sure that everything is ok
    fun showResults(){
        setShowLoading(true)
        if (validate()) {
            CoroutineScope(Dispatchers.Default).launch {
                    appendResults()
                    setShowLoading(false)
                    dataReady.postValue(true)
            }
        } else {
            Toast.makeText(context, "Can't Travel to the same place", Toast.LENGTH_SHORT)
                .show()
            setShowLoading(false)
        }
    }

    //ML model prediction fun
    private fun predict() =
        pythonRunner.predict(InputData(month.toFloat(), day.toFloat(), time1.toFloat(), time2.toFloat()))


    //navigation functions
    fun navigateToResult(){
        navController?.navigate(Pages.ResultsPage.toString())
    }

    fun navigateBack(){
        navController?.navigateUp()
    }

    private fun getCurrentDate(): String {
        val currentDate = LocalDate.now()
        val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return dateFormat.format(currentDate)
    }

    companion object{
        private const val passengersMaxToBePicked = 5
    }
}