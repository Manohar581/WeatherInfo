package com.app.weatherinfo.api

import com.app.weatherinfo.weather.model.WeatherResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getWeatherInfo(city: String, appID : String): Response<WeatherResponse> =
        apiService.getWeatherInfo(city, appID)

}