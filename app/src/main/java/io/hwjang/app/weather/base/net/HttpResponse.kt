package io.hwjang.app.weather.base.net

sealed class HttpResponse<out T> {
    data class Success<out T>(val data: T) : HttpResponse<T>()
    data class Error(val t: Exception) : HttpResponse<Nothing>()
    object Loading : HttpResponse<Nothing>()
}

fun <T> HttpResponse<T>.getData(): T {
    return (this as HttpResponse.Success<T>).data
}

fun <T> HttpResponse<T>.getOrNull(): T? {
    return if (success) getData() else null
}

val <T> HttpResponse<T>.exception: Exception
    get() = (this as HttpResponse.Error).t

val <T> HttpResponse<T>.success: Boolean
    get() = (this is HttpResponse.Success<T>)

val <T> HttpResponse<T>.isLoading: Boolean
    get() = (this is HttpResponse.Loading)
val <T> HttpResponse<T>.isError: Boolean
    get() = (this is HttpResponse.Error)