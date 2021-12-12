package io.github.diduseetheocean.forecastweatherapp.data.model

data class WeatherHourlyModel(
    val temp: Float,
    val rain: Float,
    val datetime: Long,
    val icon: String
)