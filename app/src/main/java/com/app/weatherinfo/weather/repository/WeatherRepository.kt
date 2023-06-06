package com.app.weatherinfo.weather.repository

import com.app.weatherinfo.api.ApiHelper
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getWeatherInfo(city: String, appId : String) = apiHelper.getWeatherInfo(city, appId)
}