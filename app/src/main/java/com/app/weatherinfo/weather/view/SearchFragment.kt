package com.app.weatherinfo.weather.view

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.app.weatherinfo.R
import com.app.weatherinfo.api.Status
import com.app.weatherinfo.databinding.FragmentSearchBinding
import com.app.weatherinfo.databinding.FragmentSplashBinding
import com.app.weatherinfo.weather.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val weatherViewModel: WeatherViewModel by viewModels()
    private var name: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        binding.etCountryName.doOnTextChanged { text, start, before, count ->
            name = text.toString()
        }
        binding.btnSearch.setOnClickListener {
            validate()
        }
    }

    private fun setObserver() {
        weatherViewModel.weatherInfoLiveData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                Status.ERROR -> Toast.makeText(context, "city not found", Toast.LENGTH_SHORT).show()
                Status.LOADING -> Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                Status.COMPLETED -> Toast.makeText(context, "Completed", Toast.LENGTH_SHORT).show()

            }
        })
    }

    private fun validate() {
        if (TextUtils.isEmpty(binding.etCountryName.text)) {
            Toast.makeText(context, "City can not be empty", Toast.LENGTH_SHORT).show()
        } else {
            // Toast.makeText(context, "Clicked $name", Toast.LENGTH_SHORT).show()
            weatherViewModel.getWeatherInfo(name.toString())
        }
    }
}