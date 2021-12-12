package io.github.diduseetheocean.forecastweatherapp.data.repository.openweather.onecall

import io.github.diduseetheocean.forecastweatherapp.data.model.WeatherLocationModel
import kotlinx.coroutines.flow.Flow

interface OpenWeatherRepositoryInterface {
    fun search(lat: Float, lon: Float): Flow<WeatherLocationModel>
}