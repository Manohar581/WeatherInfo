package com.app.weatherinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.app.weatherinfo.databinding.ActivityMainBinding
import com.app.weatherinfo.weather.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding
     private val weatherViewModel : WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        weatherViewModel.getWeatherInfo("London")
    }
}