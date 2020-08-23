package io.hwjang.app.weather.domain.usecase

import io.hwjang.app.weather.base.usecase.FlowUseCase
import io.hwjang.app.weather.data.model.City
import io.hwjang.app.weather.data.net.api.WeatherService
import javax.inject.Inject

class LocationSearchUseCase @Inject constructor(private val weatherService: WeatherService) :
    FlowUseCase<String, List<City>>() {
    override suspend fun execute(parameter: String): List<City> {
        return weatherService.getLocationSearch(parameter)
    }

}