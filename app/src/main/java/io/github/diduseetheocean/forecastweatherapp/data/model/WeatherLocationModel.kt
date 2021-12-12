package io.github.diduseetheocean.forecastweatherapp.data.model

data class WeatherLocationModel (
    val weatherDailyModelList: List<WeatherDailyModel>,
    val weatherHourlyModelList: List<WeatherHourlyModel>
)