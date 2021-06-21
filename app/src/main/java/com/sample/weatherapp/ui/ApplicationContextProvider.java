package com.sample.weatherapp.ui;

import android.app.Application;

import com.sample.weatherapp.WeatherApplication;

/**
 *
 */
public class ApplicationContextProvider {

	private static volatile ApplicationContextProvider instance;

	private Application mApplication;

	private ApplicationContextProvider() {	}

	public static ApplicationContextProvider getInstance(){
		ApplicationContextProvider localInstance = instance;
		if(localInstance == null){
			synchronized (ApplicationContextProvider.class){
				localInstance = instance;
				if(localInstance == null){
					instance = localInstance = new ApplicationContextProvider();
				}
			}
		}
		return localInstance;
	}

	public Application getApplication(){
		synchronized (ApplicationContextProvider.class){
			return mApplication;
		}
	}

	public WeatherApplication getWeatherApplication(){
		synchronized (ApplicationContextProvider.class){
			if(mApplication instanceof WeatherApplication)
				return (WeatherApplication) mApplication;
			return null;
		}
	}

	public void setApplication(Application application) {
		synchronized (ApplicationContextProvider.class){
			mApplication = application;
		}
	}
}
