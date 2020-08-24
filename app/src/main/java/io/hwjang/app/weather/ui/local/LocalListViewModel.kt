package io.hwjang.app.weather.ui.local

import android.os.Build
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import io.hwjang.app.weather.base.BaseViewModel
import io.hwjang.app.weather.base.net.*
import io.hwjang.app.weather.data.model.City
import io.hwjang.app.weather.data.model.Location
import io.hwjang.app.weather.data.net.api.WeatherService
import io.hwjang.app.weather.domain.usecase.GetConsolidatedWeatherUseCase
import io.hwjang.app.weather.domain.usecase.LocationSearchUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class LocalListViewModel @ViewModelInject constructor(
    private val locationSearchUseCase: LocationSearchUseCase,
    val weatherService: WeatherService
) :
    BaseViewModel() {
    private val _query = MutableLiveData<String>()

    private val _swipeLoading = MutableLiveData(false)
    val swipeLoading: LiveData<Boolean>
        get() = _swipeLoading
    val result = MutableLiveData<List<Location?>>()
    private val localWeatherList = mutableListOf<Location?>()

    private val cityList = Transformations.switchMap(_query) { q ->
        q?.let {
            locationSearchUseCase(q)
                .onEach { response ->
                    when (response) {
                        is HttpResponse.Loading -> {
                            onLoadingStart()
                        }
                        is HttpResponse.Error -> {
                            onLoadingStop()
                            onError(response.exception)
                        }
                    }
                }
                .filter {
                    it.success
                }
                .map {
                    it.getData()
                }
                .asLiveData()
        } ?: liveData {
            emit(listOf())
        }
    }

    var request = 0

    init {
        viewModelScope.launch {
            cityList.asFlow()
                .onEach {
                    localWeatherList.clear()
                    request = it.size
                }.flatMapMerge {
                    it.asFlow()
                }.collect {
                    getLocalWeatherData(it)
                }
        }
    }


    private fun getLocalWeatherData(city: City) {
        viewModelScope.launch {
            httpGet {
                weatherService.getConsolidatedWeather(city.woeid)
            }.filter {
                !it.isLoading
            }.collect {
                request--
                localWeatherList.add(it.getOrNull())
                if (it.isError) {
                    onError(it.exception)
                }
                if (request < 1) {
                    val sorter = cityList.value?.map { city ->
                        city.woeid
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        localWeatherList.sortWith(Comparator.comparingInt { l ->
                            sorter?.indexOf(l?.woeid) ?: 0
                        })
                    }
                    // header
                    localWeatherList.add(0, null)
                    result.postValue(localWeatherList.toList())
                    onLoadingStop()
                }
            }
        }
    }


    fun load() {
        search("se")
    }

    private fun search(query: String?) {
        _query.value = query
    }

    fun onSwipeRefresh() {
        if (isLoading()) {
            _swipeLoading.value = false
            return
        }
        _swipeLoading.value = true
        result.value = emptyList()
        load()
    }

    private fun isSwipeLoading() = _swipeLoading.value!!

    private fun onLoadingStart() {
        if (!isSwipeLoading()) {
            setLoading(true)
        }
    }

    private fun onLoadingStop() {
        if (isSwipeLoading()) {
            _swipeLoading.postValue(false)
        }
        setLoading(false)
    }
}

