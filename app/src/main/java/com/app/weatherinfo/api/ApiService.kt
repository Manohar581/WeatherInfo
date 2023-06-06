package com.app.weatherinfo.api

import com.app.weatherinfo.weather.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/data/2.5/weather?")
    suspend fun getWeatherInfo(
        @Query("q") city: String,
        @Query("APPID") appId: String
    ): Response<WeatherResponse>
}