package com.sample.simpsonsviewer.utility

import android.app.Activity
import android.content.Context
import android.telephony.TelephonyManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sample.weatherapp.R


class Utils {

    companion object {

        fun loadImage(activity: Activity, imageUrl: String, ivActorInfoImage: ImageView) {
            Glide.with(activity)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .circleCrop()
                .into(ivActorInfoImage)
        }

        fun View.hideKeyboard(inputMethodManager: InputMethodManager) {
            inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
        }

    }
}