package com.sample.weatherapp.ui.weatherInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.sample.simpsonsviewer.utility.BundleTags
import com.sample.weatherapp.MainActivity
import com.sample.weatherapp.R
import com.sample.weatherapp.databinding.FragmentWeatherInfoBinding
import com.sample.weatherapp.databinding.FragmentWeatherListBinding
import com.sample.weatherapp.ui.ApplicationContextProvider
import com.sample.weatherapp.ui.weatherList.WeatherListAdapter


class WeatherInfoFragment : Fragment() {

    companion object {
        /**
         * Tag to search fragment in [FragmentManager].
         */
        val FRAGMENT_TAG = WeatherInfoFragment::class.java.name
    }

    lateinit var binding: FragmentWeatherInfoBinding;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_weather_info, container, false)
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        var weatherResponseData =
            ApplicationContextProvider.getInstance().weatherApplication.getWeatherResponseDate();
        if (weatherResponseData != null) {
            if (arguments != null) {
                val index = requireArguments().getInt(BundleTags.WEATHER_LIST_SELECTED_ITEM_INDEX);
                binding.listItem = weatherResponseData.list!!.get(index)

            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).updateBackButtonStatus(true)
    }

}