package io.hwjang.app.weather.data.model


import com.google.gson.annotations.SerializedName

data class ConsolidatedWeather(
    @SerializedName("air_pressure")
    val airPressure: Double,
    @SerializedName("applicable_date")
    val applicableDate: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("id")
    val id: Long,
    @SerializedName("max_temp")
    val maxTemp: Double,
    @SerializedName("min_temp")
    val minTemp: Double,
    @SerializedName("predictability")
    val predictability: Int,
    @SerializedName("the_temp")
    val theTemp: Double,
    @SerializedName("visibility")
    val visibility: Double,
    @SerializedName("weather_state_abbr")
    val weatherStateAbbr: String,
    @SerializedName("weather_state_name")
    val weatherStateName: String,
    @SerializedName("wind_direction")
    val windDirection: Double,
    @SerializedName("wind_direction_compass")
    val windDirectionCompass: String,
    @SerializedName("wind_speed")
    val windSpeed: Double
) {
    fun getWeatherIcon() = when (weatherStateAbbr) {
        "sn" -> WeatherState.SNOW
        "sl" -> WeatherState.SLEET
        "h" -> WeatherState.HAIL
        "t" -> WeatherState.THUNDERSTORM
        "hr" -> WeatherState.HEAVY_RAIN
        "lr" -> WeatherState.LIGHT_RAIN
        "s" -> WeatherState.SHOWERS
        "hc" -> WeatherState.HEAVY_CLOUD
        "lc" -> WeatherState.LIGHT_CLOUD
        else -> WeatherState.CLEAR
    }
}