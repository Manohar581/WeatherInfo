package com.app.weatherinfo.weather.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.weatherinfo.R
import com.app.weatherinfo.api.Status
import com.app.weatherinfo.databinding.FragmentDetailsBinding
import com.app.weatherinfo.weather.model.WeatherResponse
import com.app.weatherinfo.weather.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val weatherViewModel: WeatherViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
    }

    private fun setObserver() {
        weatherViewModel.weatherInfoLiveData.observe(viewLifecycleOwner, Observer {
            populateUI(it.data)
        })
    }

    private fun populateUI(weatherResponse: WeatherResponse?) {
        weatherResponse.let {
            binding.tvCity.text = it!!.name
            binding.tvDescription.text = it!!.weather[0].description
        }
    }
}