package com.desafiolatam.weatherlatam_test2_cris_vigorena.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.desafiolatam.weatherlatam_test2_cris_vigorena.data.WeatherRepositoryImp

class WeatherViewModelFactory(private val repository: WeatherRepositoryImp) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return WeatherViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}