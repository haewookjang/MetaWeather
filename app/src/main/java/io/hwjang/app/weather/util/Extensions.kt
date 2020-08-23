package io.hwjang.app.weather.util

import android.content.Context
import android.util.TypedValue
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.toast(@StringRes resId: Int) {
    Toast.makeText(requireContext(), resId, Toast.LENGTH_LONG).show()
}


fun Float.dpToPx(context: Context): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this, context.resources.displayMetrics
    )
}