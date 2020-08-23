package io.hwjang.app.weather.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import io.hwjang.app.weather.BR

open class DataBindingViewHolder<T, DB : ViewDataBinding>(private val binding: DB) :
    RecyclerView.ViewHolder(binding.root) {

    open fun bind(item: T, position: Int) {
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }

}