package com.sample.weatherapp.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.weatherapp.model.SearchCityItem
import com.sample.weatherapp.model.WeatherResponseData
import com.sample.weatherapp.ui.WeatherRepository
import com.sample.weatherapp.utility.Constants
import retrofit2.Call
import retrofit2.Response

class SearchViewModel constructor(private val repository: WeatherRepository) : ViewModel() {

    val responseData = MutableLiveData<WeatherResponseData>()

    var searchcity = MutableLiveData<String>()

    var progressStatus = MutableLiveData<Boolean>()


    /**
     * This method is to get weather details for user input city.
     */
    fun getWebServiceDetails() {
        progressStatus.value = true
        val response = repository.getWeatherDetails(searchcity.value!!, Constants.APP_ID)
        response.enqueue(object : retrofit2.Callback<WeatherResponseData> {

            override fun onFailure(call: Call<WeatherResponseData>, t: Throwable) {
                Log.e("Error", t.toString())
                progressStatus.value = false
            }

            override fun onResponse(
                call: Call<WeatherResponseData>,
                response: Response<WeatherResponseData>
            ) {
                progressStatus.value = false
                responseData.postValue(response.body())
            }

        });

    }

}