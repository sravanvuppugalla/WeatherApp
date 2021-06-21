package com.sample.weatherapp.ui.search

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sample.simpsonsviewer.retrofit.ApiService
import com.sample.weatherapp.MainActivity
import com.sample.weatherapp.R
import com.sample.weatherapp.databinding.FragmentSearchBinding
import com.sample.weatherapp.ui.ApplicationContextProvider
import com.sample.weatherapp.ui.ViewModelFactory
import com.sample.weatherapp.ui.WeatherRepository
import com.sample.weatherapp.ui.weatherList.WeatherListFragment


class SearchFragment : Fragment() {

    companion object {
        /**
         * Tag to search fragment in [FragmentManager].
         */
        val FRAGMENT_TAG = SearchFragment::class.java.name
    }

    private val apiService = ApiService.getInstance()
    lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
//        binding.setLifecycleOwner(this)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView();
    }

    private fun initView() {
        binding.searchViewModel =
            ViewModelProvider(this, ViewModelFactory(WeatherRepository(apiService))).get(
                SearchViewModel::class.java
            )
        binding?.searchViewModel!!.responseData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                if (context != null) {
                    val fillColor = ContextCompat.getColor(requireContext(), R.color.purple_700);
                    val bitmap =
                        BitmapFactory.decodeResource(resources, R.drawable.ic_done_white_48dp);
                    binding.btnProgressLookUp.doneLoadingAnimation(fillColor, bitmap);
                    ApplicationContextProvider.getInstance().weatherApplication.setWeatherResponseDate(
                        it
                    )
                    if (activity is MainActivity && !(activity as MainActivity).isListShown()) {
                        Log.e("List Screen", "loaded")
                        (activity as MainActivity).loadFragment(
                            WeatherListFragment::class.java,
                            WeatherListFragment.FRAGMENT_TAG,
                            null,
                            true
                        )
                    }
                }
            }
        })
        binding.searchViewModel!!.progressStatus.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.btnProgressLookUp.hideKeyboard()
                binding.btnProgressLookUp.startAnimation()
                (activity as MainActivity).updateTitle(binding.etCityInput.text.toString())
            }
        })
        binding.searchViewModel!!.searchcity.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                binding.btnProgressLookUp.visibility = View.GONE
            } else {
                binding.btnProgressLookUp.visibility = View.VISIBLE
            }
        })
    }

    fun View.hideKeyboard() {
        val inputManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).updateBackButtonStatus(false)
        (activity as MainActivity).updateTitle(resources.getString(R.string.app_name))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.searchViewModel!!.responseData.removeObservers(this);
    }


}