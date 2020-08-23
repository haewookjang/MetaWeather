package io.hwjang.app.weather.domain.usecase

import io.hwjang.app.weather.base.usecase.FlowUseCase
import io.hwjang.app.weather.data.model.Location
import io.hwjang.app.weather.data.net.api.WeatherService
import javax.inject.Inject

class GetConsolidatedWeatherUseCase @Inject constructor(private val weatherService: WeatherService) :
    FlowUseCase<Int, Location>() {
    override suspend fun execute(parameter: Int): Location {
        return weatherService.getConsolidatedWeather(parameter)
    }


}