package com.desafiolatam.weatherlatam_test2_cris_vigorena.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.desafiolatam.weatherlatam_test2_cris_vigorena.data.WeatherRepositoryImp
import com.desafiolatam.weatherlatam_test2_cris_vigorena.data.remote.ServiceResponse
import com.desafiolatam.weatherlatam_test2_cris_vigorena.model.WeatherDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepositoryImp) : ViewModel() {

    suspend fun getRemoteWeatherData(): StateFlow<UIState<*>> {

        val data: MutableStateFlow<UIState<WeatherDto?>> = MutableStateFlow(UIState.Loading(true))

        viewModelScope.launch {
            repository.getRemoteWeatherData().collectLatest { response ->
                when (response) {
                    is ServiceResponse.Loading -> data.value = UIState.Loading(true)
                    is ServiceResponse.Success -> data.value = UIState.Success(response.data)
                    is ServiceResponse.Error -> data.value = UIState.Error(response.error)
                }
            }
        }
        return data
    }

    suspend fun getWeather() = repository.getWeatherData().stateIn(viewModelScope)

    suspend fun getWeatherDataById(id: Int) =
        repository.getWeatherDataById(id).stateIn(viewModelScope)
}

sealed class UIState<T> {
    class Loading<T>(val isLoading: Boolean) : UIState<T>()
    class Success<T>(val data: T) : UIState<T>()
    class Error<T>(val error: String) : UIState<T>()
}
