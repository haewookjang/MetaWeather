package io.hwjang.app.weather.data.model

import androidx.annotation.DrawableRes
import io.hwjang.app.weather.R

enum class WeatherState(@DrawableRes val drawable: Int) {
    SNOW(R.drawable.ic_weather_state_sn),
    SLEET(R.drawable.ic_weather_state_sl),
    HAIL(R.drawable.ic_weather_state_h),
    THUNDERSTORM(R.drawable.ic_weather_state_t),
    HEAVY_RAIN(R.drawable.ic_weather_state_hr),
    LIGHT_RAIN(R.drawable.ic_weather_state_lr),
    SHOWERS(R.drawable.ic_weather_state_s),
    HEAVY_CLOUD(R.drawable.ic_weather_state_hc),
    LIGHT_CLOUD(R.drawable.ic_weather_state_lc),
    CLEAR(R.drawable.ic_weather_state_c)

}