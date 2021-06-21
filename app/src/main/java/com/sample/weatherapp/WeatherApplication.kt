package com.sample.weatherapp

import android.app.Application
import com.sample.weatherapp.model.WeatherResponseData
import com.sample.weatherapp.ui.ApplicationContextProvider

class WeatherApplication : Application() {

    private var weatherResponseData: WeatherResponseData? = null

    override fun onCreate() {
        super.onCreate()
        ApplicationContextProvider.getInstance().application = this
    }

    fun setWeatherResponseDate(data: WeatherResponseData?) {
        weatherResponseData = data;
    }

    fun getWeatherResponseDate(): WeatherResponseData? {
        return weatherResponseData
    }

}