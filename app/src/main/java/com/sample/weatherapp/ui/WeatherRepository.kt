package com.sample.weatherapp.ui

import com.sample.simpsonsviewer.retrofit.ApiService
import com.sample.weatherapp.model.City

class WeatherRepository constructor(private val retrofitService: ApiService) {

    fun getWeatherDetails(strCity: String, strAppId: String) =
        retrofitService.getWeatherDetails(strCity, strAppId)
}
