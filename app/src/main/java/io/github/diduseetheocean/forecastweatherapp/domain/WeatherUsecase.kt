package io.github.diduseetheocean.forecastweatherapp.domain

import io.github.diduseetheocean.forecastweatherapp.data.model.WeatherLocationModel
import io.github.diduseetheocean.forecastweatherapp.data.repository.openweather.onecall.OpenWeatherRepository
import io.github.diduseetheocean.forecastweatherapp.data.repository.openweather.onecall.OpenWeatherRepositoryInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class WeatherUsecase(
    private val openWeatherRepository: OpenWeatherRepositoryInterface = OpenWeatherRepository
) {
    operator fun invoke(lat: Float, lon: Float): Flow<SearchState> {
        return openWeatherRepository.search(lat, lon).process()
    }
}

private fun Flow<WeatherLocationModel>.process() = run {
    this.map {
        if (it.weatherDailyModelList.isEmpty() && it.weatherHourlyModelList.isEmpty()) {
            SearchState.Empty
        } else SearchState.Success(it)
    }
        .onStart { emit(SearchState.Loading) }
        .catch { emit(SearchState.Error(it)) }
}

sealed class SearchState {
    object Empty : SearchState()
    class Success(val weatherLocationModel: WeatherLocationModel) : SearchState()
    object Loading : SearchState()
    class Error(val throwable: Throwable) : SearchState()
}