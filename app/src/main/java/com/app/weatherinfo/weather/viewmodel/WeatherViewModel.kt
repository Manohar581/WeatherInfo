package com.app.weatherinfo.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.weatherinfo.util.Constants
import com.app.weatherinfo.util.Resource
import com.app.weatherinfo.weather.model.WeatherResponse
import com.app.weatherinfo.weather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    private val _weatherInfoLiveData = MutableLiveData<Resource<WeatherResponse>>()
    val weatherInfoLiveData: LiveData<Resource<WeatherResponse>>
        get() = _weatherInfoLiveData

    fun getWeatherInfo(city: String) {
        viewModelScope.launch {
            _weatherInfoLiveData.postValue(Resource.loading(null))
            weatherRepository.getWeatherInfo(city, Constants.APP_ID).let {
                if (it.isSuccessful) {
                    _weatherInfoLiveData.postValue(Resource.success(it.body()))
                } else {
                    _weatherInfoLiveData.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        }
    }

}