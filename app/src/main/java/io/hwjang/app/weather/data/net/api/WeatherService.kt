package io.hwjang.app.weather.data.net.api

import io.hwjang.app.weather.data.model.City
import io.hwjang.app.weather.data.model.Location
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    companion object {
        const val BASE_URL = "https://www.metaweather.com/"
    }

    @GET("/api/location/search/")
    suspend fun getLocationSearch(@Query("query") query: String): List<City>

    @GET("/api/location/{woeid}/")
    suspend fun getConsolidatedWeather(@Path("woeid") woeid: Int): Location

}