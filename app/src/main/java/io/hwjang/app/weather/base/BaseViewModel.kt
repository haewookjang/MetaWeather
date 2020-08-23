package io.hwjang.app.weather.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.hwjang.app.weather.base.net.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

import kotlin.Exception


abstract class BaseViewModel : ViewModel() {

    private val _loading = MutableLiveData(false)
    open val loading: LiveData<Boolean>
        get() = _loading

    open fun setLoading(loading: Boolean) {
        _loading.postValue(loading)
    }

    fun isLoading() = _loading.value == true

    private val _error = MutableLiveData<Exception>()
    open val error: LiveData<Exception>
        get() = _error

    fun onError(exception: Exception) {
        _error.postValue(exception)
    }

    @ExperimentalCoroutinesApi
    suspend fun <T> httpGet(call: suspend () -> T): Flow<HttpResponse<T>> =
        flow {
            try {
                emit(HttpResponse.Loading)
                emit(HttpResponse.Success(call()))
            } catch (e: Exception) {
                emit(HttpResponse.Error(e))
            }
        }.flowOn(Dispatchers.IO)

}

public fun <R, T : HttpResponse<R>> Flow<T>.viewModel(vm: BaseViewModel): Flow<HttpResponse<R>> =
    flow {
        collect { value ->
            vm.setLoading(value.isLoading)
            if (value.isError) {
                vm.onError(value.exception)
            }
            if (value.success) {
                emit(value)
            }
        }
    }
