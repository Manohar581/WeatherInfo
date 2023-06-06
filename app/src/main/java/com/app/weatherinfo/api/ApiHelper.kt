package com.app.weatherinfo.api

import com.app.weatherinfo.weather.model.WeatherResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun getWeatherInfo(city: String, appID : String): Response<WeatherResponse>
}