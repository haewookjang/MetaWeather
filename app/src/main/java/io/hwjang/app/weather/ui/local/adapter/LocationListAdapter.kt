package io.hwjang.app.weather.ui.local.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import io.hwjang.app.weather.R
import io.hwjang.app.weather.base.adapter.BaseDataBindingAdapter
import io.hwjang.app.weather.base.adapter.DataBindingViewHolder
import io.hwjang.app.weather.data.model.Location
import io.hwjang.app.weather.data.net.api.WeatherService
import io.hwjang.app.weather.databinding.ListItemLocalBinding
import javax.inject.Inject

class LocationListAdapter @Inject constructor(val weatherService: WeatherService) :
    BaseDataBindingAdapter<Location, ViewDataBinding>(CALLBACK) {
    companion object {
        val CALLBACK = object : DiffUtil.ItemCallback<Location>() {
            override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
                return oldItem.parent.woeid == newItem.parent.woeid
            }

            override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
                return areItemsTheSame(oldItem, newItem)
            }
        }
    }

    override fun createDataBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
    }

    override fun createDataBindingViewHolder(binding: ViewDataBinding): DataBindingViewHolder<Location, ViewDataBinding> {
        return if (binding is ListItemLocalBinding) {
            LocationViewHolder(binding)
        } else {
            HeaderViewHolder(binding)
        }
    }

    override fun getDataBindingLayout(position: Int): Int {
        return when (position) {
            0 -> R.layout.list_item_header
            else -> R.layout.list_item_local
        }
    }

    override fun getItemCount(): Int {
        val count = super.getItemCount()
        return if (count == 0) 0 else count + 1
    }

    fun getHeaderViewType() = getDataBindingLayout(0)
    override fun onBindViewHolder(
        holder: DataBindingViewHolder<Location, ViewDataBinding>,
        position: Int
    ) {
        if (position > 0) {
            getItem(position-1)?.let {
                holder.bind(it, position)
            }
        }
    }
}