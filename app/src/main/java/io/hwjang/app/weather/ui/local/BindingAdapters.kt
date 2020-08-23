package io.hwjang.app.weather.ui.local

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import io.hwjang.app.weather.data.model.WeatherState

@BindingAdapter("weatherIcon")
fun setWeatherStateIcon(view: ImageView, state: WeatherState?) {

    if (state==null){
        view.setImageDrawable(null)
    }else {
        view.setImageResource(state.drawable)
    }

}