package io.github.diduseetheocean.forecastweatherapp.data.repository.openweather.onecall

import io.github.diduseetheocean.forecastweatherapp.data.api.openweather.OpenWeatherApi
import io.github.diduseetheocean.forecastweatherapp.data.api.openweather.onecall.conversion.toWeatherLocationModel
import io.github.diduseetheocean.forecastweatherapp.data.api.openweather.openWeatherApi
import io.github.diduseetheocean.forecastweatherapp.data.model.WeatherLocationModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

object OpenWeatherRepository : OpenWeatherRepositoryInterface {

    override fun search(lat: Float, lon: Float): Flow<WeatherLocationModel> =
        flow {
            try {
                openWeatherApi().search(
                    lat = lat,
                    lon = lon
                ).let {
                    emit(it.toWeatherLocationModel())
                }
            } catch (e: Exception) {
                Timber.e("Exception: $e")
            }
        }
}