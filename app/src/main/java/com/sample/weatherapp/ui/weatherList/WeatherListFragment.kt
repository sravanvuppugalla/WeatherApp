package com.sample.weatherapp.ui.weatherList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.simpsonsviewer.utility.BundleTags
import com.sample.weatherapp.MainActivity
import com.sample.weatherapp.R
import com.sample.weatherapp.databinding.FragmentWeatherListBinding
import com.sample.weatherapp.model.ListItem
import com.sample.weatherapp.model.WeatherResponseData
import com.sample.weatherapp.ui.ApplicationContextProvider
import com.sample.weatherapp.ui.weatherInfo.WeatherInfoFragment

class WeatherListFragment : Fragment() , CustomClickListener {

    companion object {
        /**
         * Tag to search fragment in [FragmentManager].
         */
        val FRAGMENT_TAG = WeatherListFragment::class.java.name
    }

    lateinit var binding: FragmentWeatherListBinding;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_weather_list, container, false)
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews();
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).updateBackButtonStatus(true)
    }

    private fun initViews() {
        var weatherResponseData = ApplicationContextProvider.getInstance().weatherApplication.getWeatherResponseDate();
        if (weatherResponseData!= null){
            var adapter = WeatherListAdapter(weatherResponseData.list, this)
            binding.adapter = adapter;
        }

    }

    @BindingAdapter(value = ["setAdapter"])
    fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
        this.run {
            this.setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    override fun cardClicked(itemIndex: Int) {
        val bundle = Bundle();
        bundle.putInt(BundleTags.WEATHER_LIST_SELECTED_ITEM_INDEX, itemIndex)
        if (activity is MainActivity){
            (activity as MainActivity).loadFragment(WeatherInfoFragment::class.java, WeatherInfoFragment.FRAGMENT_TAG, bundle, true);
        }
    }



}