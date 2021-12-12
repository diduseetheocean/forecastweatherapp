package io.github.diduseetheocean.forecastweatherapp.domain

import io.github.diduseetheocean.forecastweatherapp.data.model.WeatherDailyModel
import io.github.diduseetheocean.forecastweatherapp.data.model.WeatherLocationModel
import io.github.diduseetheocean.forecastweatherapp.data.repository.openweather.onecall.OpenWeatherRepositoryInterface
import io.mockk.mockk
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.net.UnknownHostException

class WeatherUsecaseStatesTest {

    @Test
    fun `search Should return SearchState Loading`() = runBlocking {
        val hit = mockk<WeatherLocationModel>()
        val repository = mockRepository(flowOf(hit))

        val result =
            WeatherUsecase(repository).invoke(lat = 33.44f, lon = -94.04f).first()

        assert((result is SearchState.Loading))
    }

    @Test
    fun `search Should return Success state`() = runBlocking {
        val repository = mockRepository(flow {
            emit(weatherLocationModelDummy)
        })

        val result = WeatherUsecase(repository).invoke(lat = 33.44f, lon = -94.04f).last()

        assert(result is SearchState.Success)
    }

    @Test
    fun `search Should return Loading then Success`() = runBlocking {
        val repository = mockRepository(flow {
            emit(weatherLocationModelDummy)
        })

        val result = WeatherUsecase(repository).invoke(lat = 33.44f, lon = -94.04f).toList()

        assert(result.first() is SearchState.Loading)
        assert(result.drop(1).first() is SearchState.Success)
        assert(result.count() == 2)
    }

    @Test
    fun `search with empty results Should return SearchState Empty`() = runBlocking {
        val repository = mockRepository(flowOf(emptyWeatherLocationModelDummy))

        val result = WeatherUsecase(repository).invoke(lat = 33.44f, lon = -94.04f).last()

        assert((result is SearchState.Empty))
    }

    @Test
    fun `search with exception Should return SearchState Error`() = runBlocking {
        val repository = mockRepository(flow {
            throw UnknownHostException()
        })

        val result = WeatherUsecase(repository).invoke(lat = 33.44f, lon = -94.04f).last()

        assert((result is SearchState.Error) && result.throwable is UnknownHostException)
    }

    private val weatherLocationModelDummy = WeatherLocationModel(
        weatherDailyModelList = listOf(
            WeatherDailyModel(
                tempDay = 0.0f,
                tempNight = 0.0f,
                description = "",
                datetime = 0,
                icon = ""
            )
        ),
        weatherHourlyModelList = emptyList()
    )

    private val emptyWeatherLocationModelDummy = WeatherLocationModel(
        weatherDailyModelList = emptyList(),
        weatherHourlyModelList = emptyList()
    )

    private fun mockRepository(flowReturn: Flow<WeatherLocationModel>) =
        object : OpenWeatherRepositoryInterface {
            override fun search(lat: Float, lon: Float): Flow<WeatherLocationModel> = flowReturn
        }
}