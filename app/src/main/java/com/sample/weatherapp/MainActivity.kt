package com.sample.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sample.simpsonsviewer.utility.Utils
import com.sample.weatherapp.databinding.ActivityMainBinding
import com.sample.weatherapp.ui.search.SearchFragment
import com.sample.weatherapp.ui.weatherList.WeatherListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG: String = MainActivity::class.java.name
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        init();
    }

    /**
     * All views and fragments loading can be done here.
     */
    private fun init() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        loadFragment(
            SearchFragment::class.java, SearchFragment.FRAGMENT_TAG,
            null,
            false
        )
    }

    /**
     * This method is to used to load the each and every fragment in app
     */
    fun loadFragment(
        clazz: Class<out Fragment?>,
        tag: String?,
        arguments: Bundle?,
        isAddToBackStack: Boolean
    ) {

        var oldFragment =
            supportFragmentManager.findFragmentByTag(tag)
        if (oldFragment == null) {
            try {
                oldFragment = clazz.newInstance()
            } catch (e: InstantiationException) {
                Log.e(TAG, "Cant instantiate fragment " + clazz)
            } catch (e: IllegalAccessException) {
                Log.e(TAG, "Cant instantiate fragment " + clazz)
            }
        }
        if (oldFragment != null) {
            if (arguments != null) {
                oldFragment.arguments = arguments
            }
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.flContainer, oldFragment, tag)

            if (isAddToBackStack) {
                transaction.addToBackStack(tag)
            }
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            transaction.commitAllowingStateLoss()
        }
    }

    fun isListShown(): Boolean {
        var infoFragment =
            supportFragmentManager.findFragmentByTag(WeatherListFragment.FRAGMENT_TAG)
        return (infoFragment is WeatherListFragment)

    }


    fun updateBackButtonStatus(isShowBackButton: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isShowBackButton)
        supportActionBar?.setHomeButtonEnabled(isShowBackButton)
    }

    fun updateTitle(title: String) {
        supportActionBar?.setTitle(title)
    }


}