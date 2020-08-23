package io.hwjang.app.weather.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import io.hwjang.app.weather.R
import io.hwjang.app.weather.util.toast
import timber.log.Timber
import java.net.UnknownHostException

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {
    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.error.observe(this, Observer {
            Timber.e(it)
            onError(it)
        })
        viewModel.loading.observe(this, Observer {
            Timber.d("loading ->$it")
        })
    }

    private fun onError(exception: Exception) {
        when (exception) {
            is UnknownHostException -> {
                toast(R.string.toast_error_network_disconnect)
            }
            else -> {
                toast(R.string.toast_error_server)
            }
        }
    }
}