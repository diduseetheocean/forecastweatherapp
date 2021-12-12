package io.github.diduseetheocean.forecastweatherapp.data.api.openweather

import io.github.diduseetheocean.forecastweatherapp.BuildConfig
import io.github.diduseetheocean.forecastweatherapp.data.api.openweather.onecall.entity.OpenWeatherResponse
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val apiUrl = "https://api.openweathermap.org/data/2.5/"

interface OpenWeatherApi {
    @GET("onecall")
    suspend fun search(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
        @Query("exclude") exclude: String = "current,minutely,alerts",
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "de_de",
    ): OpenWeatherResponse
}

fun openWeatherApi(
    baseUrl: HttpUrl = apiUrl.toHttpUrl(),
    client: () -> OkHttpClient = { makeOkHttpClient() },
): OpenWeatherApi {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client())
        .build()

    return retrofit.create(OpenWeatherApi::class.java)
}

private fun makeOkHttpClient(
    logging: () -> Interceptor = { loggingInterceptor() },
    authorization: () -> Interceptor = { authorizationInterceptor() },
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(logging())
        .addInterceptor(authorization())
        .build()

private fun authorizationInterceptor() = Interceptor {
    val url: HttpUrl = it.request().url
        .newBuilder()
        .addQueryParameter("appid", BuildConfig.api_key_openweather)
        .build()
    val request: Request = it.request().newBuilder().url(url).build()
    it.proceed(request)
}

private fun loggingInterceptor(): Interceptor =
    HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }