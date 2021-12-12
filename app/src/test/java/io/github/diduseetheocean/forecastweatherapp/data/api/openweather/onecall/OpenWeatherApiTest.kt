package io.github.diduseetheocean.forecastweatherapp.data.api.openweather.onecall

import io.github.diduseetheocean.forecastweatherapp.TestUtils
import io.github.diduseetheocean.forecastweatherapp.data.api.openweather.openWeatherApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException

class OpenWeatherApiTest {

    @get:Rule
    private val mockWebServer = MockWebServer()

    private val api by lazy {
        openWeatherApi(mockWebServer.url("/"), client = {
            OkHttpClient.Builder().build()
        })
    }

    @Test
    fun `deserialization via api call should succeed`() = runBlocking {
        mockWebServer.enqueue(
            response = MockResponse()
                .setBody(TestUtils.loadJsonFile("OpenWeatherOneCallSearchResponse.json"))
                .setResponseCode(200)
        )
        val search = api.search(lat = 33.44f, lon = -94.04f)

        Assert.assertTrue(search.timezone == "America/Chicago")
    }

    @Test
    fun `fetching of api call should return http error`(): Unit = runBlocking {
        mockWebServer.enqueue(
            response = MockResponse()
                .setBody(TestUtils.loadJsonFile("OpenWeatherOneCallSearchResponse.json"))
                .setResponseCode(400)
        )

        Assert.assertThrows(HttpException::class.java) {
            runBlocking {
                api.search(lat = 33.44f, lon = -94.04f)
            }
        }.also {
            Assert.assertEquals(400, it.code())
        }
    }
}