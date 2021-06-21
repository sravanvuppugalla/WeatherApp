package com.sample.weatherapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.weatherapp.ui.search.SearchViewModel

class ViewModelFactory constructor(private val repository: WeatherRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            SearchViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}