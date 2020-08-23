package io.hwjang.app.weather.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.hwjang.app.weather.data.net.api.WeatherService
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class AppModule {
    @Singleton
    @Provides
    fun provideWeaderService(retrofit: Retrofit) = retrofit.create(WeatherService::class.java)

}