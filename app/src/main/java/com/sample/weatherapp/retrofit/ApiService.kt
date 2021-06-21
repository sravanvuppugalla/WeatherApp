package com.sample.simpsonsviewer.retrofit

import com.sample.weatherapp.model.WeatherResponseData
import com.sample.weatherapp.utility.Constants
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.http.Query

interface ApiService {

    @GET("forecast")
    fun getWeatherDetails(
            @Query("q") strCity: String,
            @Query("appid") strAppId: String
    ): Call<WeatherResponseData>


    companion object {

        var retrofitService: ApiService? = null

        fun getInstance(): ApiService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                retrofitService = retrofit.create(ApiService::class.java)
            }
            return retrofitService!!
        }
    }
}