package com.sample.weatherapp.model

import android.text.TextUtils

data class SearchCityItem(val strSearchCity: String?) {

    val isStrSearchCityValid: Boolean
        get() = strSearchCity != null && !TextUtils.isEmpty(strSearchCity)

}