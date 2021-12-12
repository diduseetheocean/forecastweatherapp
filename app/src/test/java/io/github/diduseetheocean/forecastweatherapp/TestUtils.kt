package io.github.diduseetheocean.forecastweatherapp

import java.io.InputStreamReader

object TestUtils {
    fun loadJsonFile(path: String): String {
        val resourceAsStream = javaClass.classLoader?.getResourceAsStream(path)
        val reader = InputStreamReader(resourceAsStream)
        return reader.use { it.readText() }
    }
}