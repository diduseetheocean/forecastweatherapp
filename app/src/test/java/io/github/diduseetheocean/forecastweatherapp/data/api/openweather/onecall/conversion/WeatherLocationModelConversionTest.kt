package io.github.diduseetheocean.forecastweatherapp.data.api.openweather.onecall.conversion

import io.github.diduseetheocean.forecastweatherapp.TestUtils
import io.github.diduseetheocean.forecastweatherapp.data.api.openweather.openWeatherApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class WeatherLocationModelConversionTest {

    @get:Rule
    private val mockWebServer = MockWebServer()

    private val api by lazy {
        openWeatherApi(mockWebServer.url("/"), client = {
            OkHttpClient.Builder().build()
        })
    }

    @Test
    fun `conversion of OpenWeatherResponse to WeatherLocationModel`() = runBlocking {
        mockWebServer.enqueue(
            response = MockResponse()
                .setBody(TestUtils.loadJsonFile("OpenWeatherOneCallSearchResponse.json"))
                .setResponseCode(200)
        )
        val search = api.search(lat = 33.44f, lon = -94.04f)

        Assert.assertEquals(
            search.toWeatherLocationModel().weatherDailyModelList.first().description,
            "clear sky"
        )
    }
}