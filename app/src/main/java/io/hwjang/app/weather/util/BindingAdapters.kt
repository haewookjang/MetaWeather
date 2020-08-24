package io.hwjang.app.weather.util

import android.os.Build
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter

@BindingAdapter("loading")
fun loading(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@RequiresApi(Build.VERSION_CODES.N)
@BindingAdapter("toHtml")
fun toHtml(tv: TextView, text: String) {
    tv.text = Html.fromHtml(text,Html.FROM_HTML_MODE_LEGACY)
}

