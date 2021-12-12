package io.github.diduseetheocean.forecastweatherapp.data.model

data class WeatherDailyModel(
    val tempDay: Float,
    val tempNight: Float,
    val description: String,
    val datetime: Long,
    val icon: String
)