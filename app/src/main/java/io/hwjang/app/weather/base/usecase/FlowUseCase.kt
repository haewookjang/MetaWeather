package io.hwjang.app.weather.base.usecase

import io.hwjang.app.weather.base.net.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<P, R> {
    @ExperimentalCoroutinesApi
    open operator fun invoke(parameter: P) = flow<HttpResponse<R>> {
        emit(HttpResponse.Loading)
        try {
            emit(HttpResponse.Success(execute(parameter)))
        } catch (e: Exception) {
            emit(HttpResponse.Error(e))
        }

    }.flowOn(Dispatchers.IO)

    abstract suspend fun execute(parameter: P): R
}